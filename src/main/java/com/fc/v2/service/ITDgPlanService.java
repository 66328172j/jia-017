package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TDgPlan;

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

    /** 新增 */
    int insertTDgPlan(TDgPlan record);

    /** 修改 */
    int updateTDgPlan(TDgPlan record);

    /** 批量删除 */
    int deleteTDgPlanByIds(String ids);

    /** 按主键删除 */
    int deleteTDgPlanById(Long id);
}
