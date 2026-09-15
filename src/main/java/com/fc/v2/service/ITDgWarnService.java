package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TDgWarn;

import java.util.List;

/**
 * 浅情预警单 Service接口
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface ITDgWarnService {

    /** 按主键查询 */
    TDgWarn selectTDgWarnById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TDgWarn> selectTDgWarnList(Wrapper<TDgWarn> queryWrapper);

    /** 新增 */
    int insertTDgWarn(TDgWarn record);

    /** 修改 */
    int updateTDgWarn(TDgWarn record);

    /** 批量删除 */
    int deleteTDgWarnByIds(String ids);

    /** 按主键删除 */
    int deleteTDgWarnById(Long id);
}
