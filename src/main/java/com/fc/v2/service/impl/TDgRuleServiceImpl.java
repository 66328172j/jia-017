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
import com.fc.v2.mapper.auto.TDgRuleMapper;
import com.fc.v2.model.auto.TDgRule;
import com.fc.v2.service.ITDgRuleService;
import com.fc.v2.util.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 浅情判定规则Service业务层处理
 *
 * @author fuce
 * @date 2026-09-16
 */
@Service
public class TDgRuleServiceImpl extends ServiceImpl<TDgRuleMapper, TDgRule> implements ITDgRuleService {

    private static final BigDecimal RATE_MIN = BigDecimal.ZERO;
    private static final BigDecimal RATE_MAX = new BigDecimal("100");

    @Override
    public TDgRule selectTDgRuleById(Long id) {
        return this.baseMapper.selectOne(new QueryWrapper<TDgRule>()
                .eq("id", id)
                .eq("del_flag", 0));
    }

    @Override
    public List<TDgRule> selectTDgRuleList(Wrapper<TDgRule> queryWrapper) {
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public int insertTDgRule(TDgRule record) {
        if (!validRule(record)) {
            return 0;
        }
        if (record.getStatus() == null) {
            record.setStatus(0);
        }
        record.setDelFlag(0);
        return this.baseMapper.insert(record);
    }

    @Override
    public int updateTDgRule(TDgRule record) {
        if (record == null || record.getId() == null || !validRule(record)) {
            return 0;
        }
        record.setDelFlag(null);
        record.setUpdateTime(new Date());
        return this.baseMapper.update(record, new UpdateWrapper<TDgRule>()
                .eq("id", record.getId())
                .eq("del_flag", 0));
    }

    @Override
    public int deleteTDgRuleByIds(String ids) {
        Long[] idArr = ConvertUtil.toLongArray(ids);
        if (idArr == null || idArr.length == 0) {
            return 0;
        }
        return this.baseMapper.deleteBatchIds(Arrays.asList(idArr));
    }

    @Override
    public int deleteTDgRuleById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    /**
     * 规则校验：编号、名称必填且编号不重复；三条分界线须在0~100之间并严格从低到高，
     * 保证同一浅点率按该规则只能落入唯一档位。
     */
    private boolean validRule(TDgRule record) {
        if (record == null) {
            return false;
        }
        if (StringUtils.isEmpty(record.getRuleCode()) || StringUtils.isEmpty(record.getRuleName())) {
            return false;
        }
        BigDecimal dev1 = record.getDev1Max();
        BigDecimal dev2 = record.getDev2Max();
        BigDecimal dev3 = record.getDev3Max();
        if (dev1 == null || dev2 == null || dev3 == null) {
            return false;
        }
        if (dev1.compareTo(RATE_MIN) < 0 || dev3.compareTo(RATE_MAX) > 0) {
            return false;
        }
        if (!(dev1.compareTo(dev2) < 0 && dev2.compareTo(dev3) < 0)) {
            return false;
        }
        QueryWrapper<TDgRule> wrapper = new QueryWrapper<TDgRule>()
                .eq("rule_code", record.getRuleCode())
                .eq("del_flag", 0);
        if (record.getId() != null) {
            wrapper.ne("id", record.getId());
        }
        Integer duplicated = this.baseMapper.selectCount(wrapper);
        return duplicated == null || duplicated == 0;
    }
}
