package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TDgWork;

import java.util.List;

/**
 * 疏浚作业单 Service接口
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface ITDgWorkService {

    /** 按主键查询 */
    TDgWork selectTDgWorkById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TDgWork> selectTDgWorkList(Wrapper<TDgWork> queryWrapper);

    /** 新增 */
    int insertTDgWork(TDgWork record);

    /** 修改 */
    int updateTDgWork(TDgWork record);

    /** 批量删除 */
    int deleteTDgWorkByIds(String ids);

    /** 按主键删除 */
    int deleteTDgWorkById(Long id);

    /** 作业流转：0开工 1完工 */
    int advanceTDgWork(Long id, Integer action, String remark);
}
