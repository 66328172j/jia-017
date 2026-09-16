package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TDgExport;

import java.util.List;

/**
 * 测量台账导出记录 Service接口
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface ITDgExportService {

    /** 按主键查询 */
    TDgExport selectTDgExportById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TDgExport> selectTDgExportList(Wrapper<TDgExport> queryWrapper);

    /** 新增 */
    int insertTDgExport(TDgExport record);

    /** 修改 */
    int updateTDgExport(TDgExport record);

    /** 批量删除 */
    int deleteTDgExportByIds(String ids);

    /** 按主键删除 */
    int deleteTDgExportById(Long id);
}
