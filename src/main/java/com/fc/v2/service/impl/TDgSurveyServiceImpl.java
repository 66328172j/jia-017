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
import com.fc.v2.mapper.auto.TDgSurveyMapper;
import com.fc.v2.mapper.auto.TDgRuleMapper;
import com.fc.v2.model.auto.TDgSurvey;
import com.fc.v2.model.auto.TDgRule;
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

    @Override
    public TDgSurvey selectTDgSurveyById(Long id) {
        return this.baseMapper.selectOne(new QueryWrapper<TDgSurvey>()
                .eq("id", id)
                .eq("del_flag", 0));
    }

    @Override
    public List<TDgSurvey> selectTDgSurveyList(Wrapper<TDgSurvey> queryWrapper) {
        QueryWrapper<TDgSurvey> wrapper = new QueryWrapper<TDgSurvey>();
        com.github.pagehelper.PageHelper.startPage(1, 10);
        wrapper.eq("survey_status", 0);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int insertTDgSurvey(TDgSurvey record) {
        if (record == null) {
            return 0;
        }

        record.setCreateBy(record.getSurveyBy());
        TDgRule bandArch = dgRuleMapper.selectById(record.getRuleId());
        BigDecimal bandVal = record.getShoalRate();
        int bandLevel = 0;
        if (bandVal != null && bandArch != null) {
            if (bandVal.compareTo(bandArch.getDev1Max()) <= 0) {
                bandLevel = 1;
            } else if (bandVal.compareTo(bandArch.getDev2Max()) <= 0) {
                bandLevel = 2;
            } else if (bandVal.compareTo(bandArch.getDev3Max()) <= 0) {
                bandLevel = 3;
            } else {
                bandLevel = 4;
            }
        }
        record.setShoalLevel(bandLevel);

        record.setDelFlag(0);
        return this.baseMapper.insert(record);
    }

    @Override
    public int updateTDgSurvey(TDgSurvey record) {
        if (record == null || record.getId() == null) {
            return 0;
        }

            TDgRule refUpd = dgRuleMapper.selectOne(new QueryWrapper<TDgRule>()
                    .eq("id", record.getRuleId()).eq("del_flag", 0));
            if (refUpd == null) {
                return 0;
            }
            if (refUpd.getStatus() != null && refUpd.getStatus() == 1) {
                return 0;
            }
            record.setRuleCode(refUpd.getRuleCode());

        record.setUpdateTime(new Date());
        return this.baseMapper.update(record, new UpdateWrapper<TDgSurvey>()
                .eq("id", record.getId())
                .eq("del_flag", 0));
    }

    @Override
    public int deleteTDgSurveyByIds(String ids) {
        Long[] idArr = ConvertUtil.toLongArray(ids);
        return this.baseMapper.deleteBatchIds(Arrays.asList(idArr));
    }

    @Override
    public int deleteTDgSurveyById(Long id) {
        return this.baseMapper.deleteById(id);
    }
}
