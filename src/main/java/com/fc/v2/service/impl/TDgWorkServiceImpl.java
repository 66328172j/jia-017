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
import com.fc.v2.mapper.auto.TDgWorkMapper;
import com.fc.v2.mapper.auto.TDgReachMapper;
import com.fc.v2.model.auto.TDgWork;
import com.fc.v2.model.auto.TDgReach;
import com.fc.v2.service.ITDgWorkService;
import com.fc.v2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 疏浚作业单Service业务层处理
 *
 * @author fuce
 * @date 2026-09-12
 */
@Service
public class TDgWorkServiceImpl extends ServiceImpl<TDgWorkMapper, TDgWork> implements ITDgWorkService {

    @Autowired
    private TDgReachMapper dgReachMapper;

    @Override
    public TDgWork selectTDgWorkById(Long id) {
        return this.baseMapper.selectOne(new QueryWrapper<TDgWork>()
                .eq("id", id)
                .eq("del_flag", 0));
    }

    @Override
    public List<TDgWork> selectTDgWorkList(Wrapper<TDgWork> queryWrapper) {
        QueryWrapper<TDgWork> wrapper = new QueryWrapper<TDgWork>();
        com.github.pagehelper.PageHelper.startPage(1, 10);
        wrapper.eq("work_status", 0);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int insertTDgWork(TDgWork record) {
        if (record == null) {
            return 0;
        }

        record.setCreateBy(record.getForeman());
        Date dayBase = record.getFinishDue();
        long dayDiff = 0L;
        if (dayBase != null) {
            dayDiff = (dayBase.getTime() - todayStart().getTime()) / 86400000L + 1;
        }
        record.setRemainDays((int) dayDiff);

        record.setDelFlag(0);
        return this.baseMapper.insert(record);
    }

    @Override
    public int updateTDgWork(TDgWork record) {
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
        return this.baseMapper.update(record, new UpdateWrapper<TDgWork>()
                .eq("id", record.getId())
                .eq("del_flag", 0));
    }

    @Override
    public int deleteTDgWorkByIds(String ids) {
        Long[] idArr = ConvertUtil.toLongArray(ids);
        return this.baseMapper.deleteBatchIds(Arrays.asList(idArr));
    }

    @Override
    public int deleteTDgWorkById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int advanceTDgWork(Long id, Integer action, String remark) {
        if (id == null || action == null) {
            return 0;
        }
        if (action == 0) {
            UpdateWrapper<TDgWork> uw = new UpdateWrapper<TDgWork>()
                    .eq("id", id).eq("del_flag", 0);
            uw.set("work_status", 1);
            return this.baseMapper.update(null, uw);
        }
        if (action == 1) {
            UpdateWrapper<TDgWork> uw = new UpdateWrapper<TDgWork>()
                    .eq("id", id).eq("del_flag", 0);
            uw.set("work_status", 2);
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
