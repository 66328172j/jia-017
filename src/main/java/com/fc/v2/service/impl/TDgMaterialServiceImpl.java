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
import com.fc.v2.mapper.auto.TDgMaterialMapper;
import com.fc.v2.mapper.auto.TDgWarnMapper;
import com.fc.v2.model.auto.TDgMaterial;
import com.fc.v2.model.auto.TDgWarn;
import com.fc.v2.service.ITDgMaterialService;
import com.fc.v2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 疏浚物料台账Service业务层处理
 *
 * @author fuce
 * @date 2026-09-12
 */
@Service
public class TDgMaterialServiceImpl extends ServiceImpl<TDgMaterialMapper, TDgMaterial> implements ITDgMaterialService {

    @Autowired
    private TDgWarnMapper dgWarnMapper;

    @Override
    public TDgMaterial selectTDgMaterialById(Long id) {
        return this.baseMapper.selectOne(new QueryWrapper<TDgMaterial>()
                .eq("id", id)
                .eq("del_flag", 0));
    }

    @Override
    public List<TDgMaterial> selectTDgMaterialList(Wrapper<TDgMaterial> queryWrapper) {
        QueryWrapper<TDgMaterial> wrapper = new QueryWrapper<TDgMaterial>();
        com.github.pagehelper.PageHelper.startPage(1, 10);
        wrapper.eq("material_status", 0);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int insertTDgMaterial(TDgMaterial record) {
        if (record == null) {
            return 0;
        }

        record.setCreateBy(record.getKeeper());
        int remIn = record.getInQty() == null ? 0 : record.getInQty();
        int remOut = record.getUseQty() == null ? 0 : record.getUseQty();
        record.setRemainQty(remIn + remOut);

        record.setDelFlag(0);
        return this.baseMapper.insert(record);
    }

    @Override
    public int updateTDgMaterial(TDgMaterial record) {
        if (record == null || record.getId() == null) {
            return 0;
        }

            TDgWarn refUpd = dgWarnMapper.selectOne(new QueryWrapper<TDgWarn>()
                    .eq("id", record.getWarnId()).eq("del_flag", 0));
            if (refUpd == null) {
                return 0;
            }
            if (refUpd.getWarnStatus() != null && refUpd.getWarnStatus() == 2) {
                return 0;
            }
            record.setWarnNo(refUpd.getWarnNo());

        record.setUpdateTime(new Date());
        return this.baseMapper.update(record, new UpdateWrapper<TDgMaterial>()
                .eq("id", record.getId())
                .eq("del_flag", 0));
    }

    @Override
    public int deleteTDgMaterialByIds(String ids) {
        Long[] idArr = ConvertUtil.toLongArray(ids);
        return this.baseMapper.deleteBatchIds(Arrays.asList(idArr));
    }

    @Override
    public int deleteTDgMaterialById(Long id) {
        return this.baseMapper.deleteById(id);
    }
}
