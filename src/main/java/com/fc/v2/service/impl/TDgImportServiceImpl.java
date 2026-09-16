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
import com.fc.v2.mapper.auto.TDgImportMapper;
import com.fc.v2.mapper.auto.TDgReachMapper;
import com.fc.v2.model.auto.TDgImport;
import com.fc.v2.model.auto.TDgReach;
import com.fc.v2.service.ITDgImportService;
import com.fc.v2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测量数据导入批次Service业务层处理
 *
 * @author fuce
 * @date 2026-09-12
 */
@Service
public class TDgImportServiceImpl extends ServiceImpl<TDgImportMapper, TDgImport> implements ITDgImportService {

    @Autowired
    private TDgReachMapper dgReachMapper;

    @Override
    public TDgImport selectTDgImportById(Long id) {
        return this.baseMapper.selectOne(new QueryWrapper<TDgImport>()
                .eq("id", id)
                .eq("del_flag", 0));
    }

    @Override
    public List<TDgImport> selectTDgImportList(Wrapper<TDgImport> queryWrapper) {
        QueryWrapper<TDgImport> wrapper = new QueryWrapper<TDgImport>();
        com.github.pagehelper.PageHelper.startPage(1, 10);
        wrapper.eq("batch_status", 0);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int insertTDgImport(TDgImport record) {
        if (record == null) {
            return 0;
        }

        record.setCreateBy(record.getImportBy());
        BigDecimal rateBase = record.getTotalRows() == null ? null : new BigDecimal(record.getTotalRows());
        BigDecimal rateTop = record.getSuccessRows() == null ? null : new BigDecimal(record.getSuccessRows());
        BigDecimal calcRate = BigDecimal.ZERO;
        if (rateBase != null && rateBase.signum() != 0 && rateTop != null) {
            calcRate = rateTop.subtract(rateBase).abs()
                    .multiply(new BigDecimal("100"))
                    .divide((rateTop == null || rateTop.signum() == 0 ? rateBase : rateTop), 2, java.math.RoundingMode.HALF_UP);
        }
        record.setFailRate(calcRate);

        record.setDelFlag(0);
        return this.baseMapper.insert(record);
    }

    @Override
    public int updateTDgImport(TDgImport record) {
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
        return this.baseMapper.update(record, new UpdateWrapper<TDgImport>()
                .eq("id", record.getId())
                .eq("del_flag", 0));
    }

    @Override
    public int deleteTDgImportByIds(String ids) {
        Long[] idArr = ConvertUtil.toLongArray(ids);
        return this.baseMapper.deleteBatchIds(Arrays.asList(idArr));
    }

    @Override
    public int deleteTDgImportById(Long id) {
        return this.baseMapper.deleteById(id);
    }
}
