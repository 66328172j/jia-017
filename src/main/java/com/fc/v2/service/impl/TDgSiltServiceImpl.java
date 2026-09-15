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
import com.fc.v2.mapper.auto.TDgSiltMapper;
import com.fc.v2.mapper.auto.TDgReachMapper;
import com.fc.v2.model.auto.TDgSilt;
import com.fc.v2.model.auto.TDgReach;
import com.fc.v2.service.ITDgSiltService;
import com.fc.v2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 回淤监测记录Service业务层处理
 *
 * @author fuce
 * @date 2026-09-12
 */
@Service
public class TDgSiltServiceImpl extends ServiceImpl<TDgSiltMapper, TDgSilt> implements ITDgSiltService {

    @Autowired
    private TDgReachMapper dgReachMapper;

    @Override
    public TDgSilt selectTDgSiltById(Long id) {
        return this.baseMapper.selectOne(new QueryWrapper<TDgSilt>()
                .eq("id", id)
                .eq("del_flag", 0));
    }

    @Override
    public List<TDgSilt> selectTDgSiltList(Wrapper<TDgSilt> queryWrapper) {
        QueryWrapper<TDgSilt> wrapper = new QueryWrapper<TDgSilt>();
        com.github.pagehelper.PageHelper.startPage(1, 10);
        wrapper.eq("silt_status", 0);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int insertTDgSilt(TDgSilt record) {
        if (record == null) {
            return 0;
        }

        record.setCreateBy(record.getMonitorBy());
        Date dayBase = record.getNextDate();
        long dayDiff = 0L;
        if (dayBase != null) {
            dayDiff = (dayBase.getTime() - todayStart().getTime()) / 86400000L + 1;
        }
        record.setRemainDays((int) dayDiff);

        record.setDelFlag(0);
        return this.baseMapper.insert(record);
    }

    @Override
    public int updateTDgSilt(TDgSilt record) {
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
        return this.baseMapper.update(record, new UpdateWrapper<TDgSilt>()
                .eq("id", record.getId())
                .eq("del_flag", 0));
    }

    @Override
    public int deleteTDgSiltByIds(String ids) {
        Long[] idArr = ConvertUtil.toLongArray(ids);
        return this.baseMapper.deleteBatchIds(Arrays.asList(idArr));
    }

    @Override
    public int deleteTDgSiltById(Long id) {
        return this.baseMapper.deleteById(id);
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
