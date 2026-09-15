package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TDgStat;

import java.util.List;

/**
 * 疏浚质量合格率统计 Service接口
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface ITDgStatService {

    /** 按主键查询 */
    TDgStat selectTDgStatById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TDgStat> selectTDgStatList(Wrapper<TDgStat> queryWrapper);

    /** 新增 */
    int insertTDgStat(TDgStat record);

    /** 修改 */
    int updateTDgStat(TDgStat record);

    /** 批量删除 */
    int deleteTDgStatByIds(String ids);

    /** 按主键删除 */
    int deleteTDgStatById(Long id);
}
