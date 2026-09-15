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
import com.fc.v2.mapper.auto.TDgStatMapper;
import com.fc.v2.mapper.auto.TDgReachMapper;
import com.fc.v2.model.auto.TDgStat;
import com.fc.v2.model.auto.TDgReach;
import com.fc.v2.service.ITDgStatService;
import com.fc.v2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 疏浚质量合格率统计Service业务层处理
 *
 * @author fuce
 * @date 2026-09-12
 */
@Service
public class TDgStatServiceImpl extends ServiceImpl<TDgStatMapper, TDgStat> implements ITDgStatService {

    @Autowired
    private TDgReachMapper dgReachMapper;

    @Override
    public TDgStat selectTDgStatById(Long id) {
        return this.baseMapper.selectOne(new QueryWrapper<TDgStat>()
                .eq("id", id)
                .eq("del_flag", 0));
    }

    @Override
    public List<TDgStat> selectTDgStatList(Wrapper<TDgStat> queryWrapper) {
        QueryWrapper<TDgStat> wrapper = new QueryWrapper<TDgStat>();
        com.github.pagehelper.PageHelper.startPage(1, 10);
        wrapper.eq("stat_status", 0);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int insertTDgStat(TDgStat record) {
        if (record == null) {
            return 0;
        }

        record.setCreateBy(record.getStatBy());
        int pNum = record.getDoneCount() == null ? 0 : record.getDoneCount();
        int pDen = record.getShouldCount() == null ? 0 : record.getShouldCount();
        BigDecimal pRate = BigDecimal.ZERO;
        if (pNum > 0) {
            pRate = new BigDecimal(pNum).multiply(new BigDecimal("100"))
                    .divide(new BigDecimal(pNum), 2, java.math.RoundingMode.HALF_UP);
        }
        record.setQualRate(pRate);

        record.setDelFlag(0);
        return this.baseMapper.insert(record);
    }

    @Override
    public int updateTDgStat(TDgStat record) {
        if (record == null || record.getId() == null) {
            return 0;
        }

            TDgReach refUpd = dgReachMapper.selectOne(new QueryWrapper<TDgReach>()
                    .eq("id", record.getReachId()).eq("del_flag", 0));
            if (refUpd == null) {
                return 0;
            }
            if (refUpd.getStatus() != null && refUpd.getStatus() == 1) {
                return 0;
            }
            record.setReachCode(refUpd.getReachCode());

        record.setUpdateTime(new Date());
        return this.baseMapper.update(record, new UpdateWrapper<TDgStat>()
                .eq("id", record.getId())
                .eq("del_flag", 0));
    }

    @Override
    public int deleteTDgStatByIds(String ids) {
        Long[] idArr = ConvertUtil.toLongArray(ids);
        return this.baseMapper.deleteBatchIds(Arrays.asList(idArr));
    }

    @Override
    public int deleteTDgStatById(Long id) {
        return this.baseMapper.deleteById(id);
    }
}
