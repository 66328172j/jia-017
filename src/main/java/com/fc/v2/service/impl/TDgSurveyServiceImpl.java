package com.fc.v2.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.common.support.ConvertUtil;
import com.fc.v2.mapper.auto.TDgReachMapper;
import com.fc.v2.mapper.auto.TDgRuleMapper;
import com.fc.v2.mapper.auto.TDgSurveyMapper;
import com.fc.v2.model.auto.TDgReach;
import com.fc.v2.model.auto.TDgRule;
import com.fc.v2.model.auto.TDgSurvey;
import com.fc.v2.service.ITDgSurveyService;
import com.fc.v2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 断面测深记录Service业务层处理
 *
 * @author fuce
 * @date 2026-09-12
 */
@Service
public class TDgSurveyServiceImpl extends ServiceImpl<TDgSurveyMapper, TDgSurvey> implements ITDgSurveyService {

    @Autowired
    private TDgRuleMapper dgRuleMapper;

    @Autowired
    private TDgReachMapper dgReachMapper;

    @Override
    public TDgSurvey selectTDgSurveyById(Long id) {
        return this.baseMapper.selectOne(new QueryWrapper<TDgSurvey>()
                .eq("id", id)
                .eq("del_flag", 0));
    }

    @Override
    public List<TDgSurvey> selectTDgSurveyList(Wrapper<TDgSurvey> queryWrapper) {
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<TDgReach> selectReachOptions() {
        return dgReachMapper.selectList(new QueryWrapper<TDgReach>()
                .eq("del_flag", 0)
                .orderByAsc("reach_code"));
    }

    @Override
    public List<TDgRule> selectRuleOptions() {
        return dgRuleMapper.selectList(new QueryWrapper<TDgRule>()
                .eq("del_flag", 0)
                .orderByAsc("rule_code"));
    }

    @Override
    public int insertTDgSurvey(TDgSurvey record) {
        if (record == null) {
            return 0;
        }
        // 浅情等级由系统按所选规则判定，表单提交的一律忽略重算
        if (!fillArchiveAndLevel(record)) {
            return 0;
        }
        record.setSurveyStatus(0);
        record.setDelFlag(0);
        record.setCreateBy(record.getSurveyBy());
        return this.baseMapper.insert(record);
    }

    @Override
    public int updateTDgSurvey(TDgSurvey record) {
        if (record == null || record.getId() == null) {
            return 0;
        }
        if (!fillArchiveAndLevel(record)) {
            return 0;
        }
        // 复核状态、删除标记不允许随表单提交被覆盖
        record.setSurveyStatus(null);
        record.setDelFlag(null);
        record.setUpdateTime(new Date());
        // 已复核的记录不允许回改
        return this.baseMapper.update(record, new UpdateWrapper<TDgSurvey>()
                .eq("id", record.getId())
                .eq("survey_status", 0)
                .eq("del_flag", 0));
    }

    @Override
    public int reviewTDgSurvey(Long id) {
        if (id == null) {
            return 0;
        }
        return this.baseMapper.update(null, new UpdateWrapper<TDgSurvey>()
                .set("survey_status", 1)
                .set("update_time", new Date())
                .eq("id", id)
                .eq("survey_status", 0)
                .eq("del_flag", 0));
    }

    @Override
    public int deleteTDgSurveyByIds(String ids) {
        Long[] idArr = ConvertUtil.toLongArray(ids);
        if (idArr == null || idArr.length == 0) {
            return 0;
        }
        Integer reviewed = this.baseMapper.selectCount(new QueryWrapper<TDgSurvey>()
                .in("id", Arrays.asList(idArr))
                .eq("survey_status", 1)
                .eq("del_flag", 0));
        if (reviewed != null && reviewed > 0) {
            return 0;
        }
        return this.baseMapper.deleteBatchIds(Arrays.asList(idArr));
    }

    @Override
    public int deleteTDgSurveyById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    /**
     * 校验登记内容并回填档案冗余与系统判定结果。
     * 河段须为维护中档案，判定规则须为启用状态且分界线合法，浅点率须在0~100之间。
     */
    private boolean fillArchiveAndLevel(TDgSurvey record) {
        if (StringUtils.isEmpty(record.getSurveyNo())
                || record.getReachId() == null
                || record.getRuleId() == null
                || record.getShoalRate() == null
                || record.getSurveyDate() == null
                || StringUtils.isEmpty(record.getSurveyBy())) {
            return false;
        }
        BigDecimal rate = record.getShoalRate();
        if (rate.compareTo(BigDecimal.ZERO) < 0 || rate.compareTo(new BigDecimal("100")) > 0) {
            return false;
        }
        TDgReach reach = dgReachMapper.selectOne(new QueryWrapper<TDgReach>()
                .eq("id", record.getReachId()).eq("del_flag", 0));
        if (reach == null || (reach.getStatus() != null && reach.getStatus() == 1)) {
            return false;
        }
        record.setReachCode(reach.getReachCode());

        TDgRule rule = dgRuleMapper.selectOne(new QueryWrapper<TDgRule>()
                .eq("id", record.getRuleId()).eq("del_flag", 0));
        if (rule == null || (rule.getStatus() != null && rule.getStatus() == 1)) {
            return false;
        }
        Integer level = computeShoalLevel(rule, rate);
        if (level == null) {
            return false;
        }
        record.setRuleCode(rule.getRuleCode());
        record.setShoalLevel(level);
        return true;
    }

    /**
     * 按规则分界线从低到高划档：不超过良好档上限为良好(1)，不超过一般档上限为一般(2)，
     * 不超过紧张档上限为紧张(3)，其余为告急(4)。分界线缺失或未严格递增时返回null。
     */
    private Integer computeShoalLevel(TDgRule rule, BigDecimal rate) {
        BigDecimal dev1 = rule.getDev1Max();
        BigDecimal dev2 = rule.getDev2Max();
        BigDecimal dev3 = rule.getDev3Max();
        if (dev1 == null || dev2 == null || dev3 == null) {
            return null;
        }
        if (!(dev1.compareTo(dev2) < 0 && dev2.compareTo(dev3) < 0)) {
            return null;
        }
        if (rate.compareTo(dev1) <= 0) {
            return 1;
        }
        if (rate.compareTo(dev2) <= 0) {
            return 2;
        }
        if (rate.compareTo(dev3) <= 0) {
            return 3;
        }
        return 4;
    }
}
