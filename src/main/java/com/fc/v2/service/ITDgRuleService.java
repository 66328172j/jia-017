package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TDgRule;

import java.util.List;

/**
 * 浅情判定规则 Service接口
 *
 * @author fuce
 * @date 2026-09-16
 */
public interface ITDgRuleService {

    /** 按主键查询 */
    TDgRule selectTDgRuleById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TDgRule> selectTDgRuleList(Wrapper<TDgRule> queryWrapper);

    /** 新增 */
    int insertTDgRule(TDgRule record);

    /** 修改 */
    int updateTDgRule(TDgRule record);

    /** 批量删除 */
    int deleteTDgRuleByIds(String ids);

    /** 按主键删除 */
    int deleteTDgRuleById(Long id);
}
