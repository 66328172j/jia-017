package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TDgSilt;

import java.util.List;

/**
 * 回淤监测记录 Service接口
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface ITDgSiltService {

    /** 按主键查询 */
    TDgSilt selectTDgSiltById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TDgSilt> selectTDgSiltList(Wrapper<TDgSilt> queryWrapper);

    /** 新增 */
    int insertTDgSilt(TDgSilt record);

    /** 修改 */
    int updateTDgSilt(TDgSilt record);

    /** 批量删除 */
    int deleteTDgSiltByIds(String ids);

    /** 按主键删除 */
    int deleteTDgSiltById(Long id);
}
