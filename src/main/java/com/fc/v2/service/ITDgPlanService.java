package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TDgPlan;
import com.fc.v2.model.auto.TDgReach;

import java.util.List;

/**
 * 疏浚计划单 Service接口
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface ITDgPlanService {

    /** 按主键查询 */
    TDgPlan selectTDgPlanById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TDgPlan> selectTDgPlanList(Wrapper<TDgPlan> queryWrapper);

    /** 河段下拉选项（未删除档案，登记页使用） */
    List<TDgReach> selectReachOptions();

    /** 新增（完成率由系统按方量计算，新单为待确认草稿） */
    int insertTDgPlan(TDgPlan record);

    /** 修改（仅待确认草稿可改，完成率由系统重算） */
    int updateTDgPlan(TDgPlan record);

    /** 归档（待确认 -> 已归档，归档后不可再改） */
    int archiveTDgPlan(Long id);

    /** 批量删除（已归档计划单不允许删除） */
    int deleteTDgPlanByIds(String ids);

    /** 按主键删除 */
    int deleteTDgPlanById(Long id);
}
