package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TDgImport;

import java.util.List;

/**
 * 测量数据导入批次 Service接口
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface ITDgImportService {

    /** 按主键查询 */
    TDgImport selectTDgImportById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TDgImport> selectTDgImportList(Wrapper<TDgImport> queryWrapper);

    /** 新增 */
    int insertTDgImport(TDgImport record);

    /** 修改 */
    int updateTDgImport(TDgImport record);

    /** 批量删除 */
    int deleteTDgImportByIds(String ids);

    /** 按主键删除 */
    int deleteTDgImportById(Long id);
}
