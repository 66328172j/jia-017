package com.fc.v2.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.common.support.ConvertUtil;
import com.fc.v2.mapper.auto.TDgPlanMapper;
import com.fc.v2.mapper.auto.TDgReachMapper;
import com.fc.v2.model.auto.TDgPlan;
import com.fc.v2.model.auto.TDgReach;
import com.fc.v2.service.ITDgPlanService;
import com.fc.v2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 疏浚计划单Service业务层处理
 *
 * @author fuce
 * @date 2026-09-12
 */
@Service
public class TDgPlanServiceImpl extends ServiceImpl<TDgPlanMapper, TDgPlan> implements ITDgPlanService {

    @Autowired
    private TDgReachMapper dgReachMapper;

    @Override
    public TDgPlan selectTDgPlanById(Long id) {
        return this.baseMapper.selectOne(new QueryWrapper<TDgPlan>()
                .eq("id", id)
                .eq("del_flag", 0));
    }

    @Override
    public List<TDgPlan> selectTDgPlanList(Wrapper<TDgPlan> queryWrapper) {
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<TDgReach> selectReachOptions() {
        return dgReachMapper.selectList(new QueryWrapper<TDgReach>()
                .eq("del_flag", 0)
                .orderByAsc("reach_code"));
    }

    @Override
    public int insertTDgPlan(TDgPlan record) {
        if (record == null) {
            return 0;
        }
        // 完成率由系统按两个方量统一计算，表单提交的一律忽略重算
        if (!fillArchiveAndRate(record)) {
            return 0;
        }
        // 新单一律登记为待确认草稿，状态不接受表单提交
        record.setPlanStatus(0);
        record.setDelFlag(0);
        record.setCreateBy(record.getPlanBy());
        return this.baseMapper.insert(record);
    }

    @Override
    public int updateTDgPlan(TDgPlan record) {
        if (record == null || record.getId() == null) {
            return 0;
        }
        if (!fillArchiveAndRate(record)) {
            return 0;
        }
        // 计划状态、删除标记不允许随表单提交被覆盖
        record.setPlanStatus(null);
        record.setDelFlag(null);
        record.setUpdateTime(new Date());
        // 已归档的计划单不允许回改
        return this.baseMapper.update(record, new UpdateWrapper<TDgPlan>()
                .eq("id", record.getId())
                .eq("plan_status", 0)
                .eq("del_flag", 0));
    }

    @Override
    public int archiveTDgPlan(Long id) {
        if (id == null) {
            return 0;
        }
        return this.baseMapper.update(null, new UpdateWrapper<TDgPlan>()
                .set("plan_status", 1)
                .set("update_time", new Date())
                .eq("id", id)
                .eq("plan_status", 0)
                .eq("del_flag", 0));
    }

    @Override
    public int deleteTDgPlanByIds(String ids) {
        Long[] idArr = ConvertUtil.toLongArray(ids);
        if (idArr == null || idArr.length == 0) {
            return 0;
        }
        Integer archived = this.baseMapper.selectCount(new QueryWrapper<TDgPlan>()
                .in("id", Arrays.asList(idArr))
                .eq("plan_status", 1)
                .eq("del_flag", 0));
        if (archived != null && archived > 0) {
            return 0;
        }
        return this.baseMapper.deleteBatchIds(Arrays.asList(idArr));
    }

    @Override
    public int deleteTDgPlanById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    /**
     * 校验登记内容并回填档案冗余与系统计算结果。
     * 计划方量须为正数，完成方量不得为负且不得超过计划方量，
     * 河段须为维护中档案，完成率=完成方量/计划方量*100，保留两位小数。
     */
    private boolean fillArchiveAndRate(TDgPlan record) {
        if (StringUtils.isEmpty(record.getDredgeNo())
                || record.getReachId() == null
                || StringUtils.isEmpty(record.getPlanMonth())
                || record.getPlanCube() == null
                || StringUtils.isEmpty(record.getPlanBy())) {
            return false;
        }
        int planCube = record.getPlanCube();
        int doneCube = record.getDoneCube() == null ? 0 : record.getDoneCube();
        if (planCube <= 0 || doneCube < 0 || doneCube > planCube) {
            return false;
        }
        TDgReach reach = dgReachMapper.selectOne(new QueryWrapper<TDgReach>()
                .eq("id", record.getReachId()).eq("del_flag", 0));
        if (reach == null || (reach.getStatus() != null && reach.getStatus() == 1)) {
            return false;
        }
        record.setReachCode(reach.getReachCode());
        record.setDoneCube(doneCube);
        record.setDoneRate(new BigDecimal(doneCube).multiply(new BigDecimal("100"))
                .divide(new BigDecimal(planCube), 2, RoundingMode.HALF_UP));
        return true;
    }
}
