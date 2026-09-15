package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TDgMaterial;

import java.util.List;

/**
 * 疏浚物料台账 Service接口
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface ITDgMaterialService {

    /** 按主键查询 */
    TDgMaterial selectTDgMaterialById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TDgMaterial> selectTDgMaterialList(Wrapper<TDgMaterial> queryWrapper);

    /** 新增 */
    int insertTDgMaterial(TDgMaterial record);

    /** 修改 */
    int updateTDgMaterial(TDgMaterial record);

    /** 批量删除 */
    int deleteTDgMaterialByIds(String ids);

    /** 按主键删除 */
    int deleteTDgMaterialById(Long id);
}
