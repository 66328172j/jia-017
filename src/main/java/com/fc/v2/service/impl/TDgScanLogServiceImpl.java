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
import com.fc.v2.mapper.auto.TDgScanLogMapper;
import com.fc.v2.mapper.auto.TDgReachMapper;
import com.fc.v2.model.auto.TDgScanLog;
import com.fc.v2.model.auto.TDgReach;
import com.fc.v2.service.ITDgScanLogService;
import com.fc.v2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 移动端现场测深登记Service业务层处理
 *
 * @author fuce
 * @date 2026-09-12
 */
@Service
public class TDgScanLogServiceImpl extends ServiceImpl<TDgScanLogMapper, TDgScanLog> implements ITDgScanLogService {

    @Autowired
    private TDgReachMapper dgReachMapper;

    @Override
    public TDgScanLog selectTDgScanLogById(Long id) {
        return this.baseMapper.selectOne(new QueryWrapper<TDgScanLog>()
                .eq("id", id)
                .eq("del_flag", 0));
    }

    @Override
    public List<TDgScanLog> selectTDgScanLogList(Wrapper<TDgScanLog> queryWrapper) {
        QueryWrapper<TDgScanLog> wrapper = new QueryWrapper<TDgScanLog>();
        com.github.pagehelper.PageHelper.startPage(1, 10);
        wrapper.eq("scan_status", 0);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int insertTDgScanLog(TDgScanLog record) {
        if (record == null) {
            return 0;
        }

        record.setCreateBy(record.getScanBy());
        Date dayBase = record.getValidDate();
        long dayDiff = 0L;
        if (dayBase != null) {
            dayDiff = (dayBase.getTime() - todayStart().getTime()) / 86400000L + 1;
        }
        record.setValidDays((int) dayDiff);

        record.setDelFlag(0);
        return this.baseMapper.insert(record);
    }

    @Override
    public int updateTDgScanLog(TDgScanLog record) {
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
            record.setReachName(refUpd.getReachName());

        record.setUpdateTime(new Date());
        return this.baseMapper.update(record, new UpdateWrapper<TDgScanLog>()
                .eq("id", record.getId())
                .eq("del_flag", 0));
    }

    @Override
    public int deleteTDgScanLogByIds(String ids) {
        Long[] idArr = ConvertUtil.toLongArray(ids);
        return this.baseMapper.deleteBatchIds(Arrays.asList(idArr));
    }

    @Override
    public int deleteTDgScanLogById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int advanceTDgScanLog(Long id, Integer action, String remark) {
        if (id == null || action == null) {
            return 0;
        }
        if (action == 0) {
            UpdateWrapper<TDgScanLog> uw = new UpdateWrapper<TDgScanLog>()
                    .eq("id", id).eq("del_flag", 0);
            uw.set("scan_status", 1);
            return this.baseMapper.update(null, uw);
        }
        if (action == 1) {
            UpdateWrapper<TDgScanLog> uw = new UpdateWrapper<TDgScanLog>()
                    .eq("id", id).eq("del_flag", 0);
            uw.set("scan_status", 2);
            return this.baseMapper.update(null, uw);
        }
        return 0;
    }

    private Date todayStart() {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
        c.set(java.util.Calendar.MINUTE, 0);
        c.set(java.util.Calendar.SECOND, 0);
        c.set(java.util.Calendar.MILLISECOND, 0);
        return c.getTime();
    }
}
