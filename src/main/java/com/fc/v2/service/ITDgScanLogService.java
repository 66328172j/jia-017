package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TDgScanLog;

import java.util.List;

/**
 * 移动端现场测深登记 Service接口
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface ITDgScanLogService {

    /** 按主键查询 */
    TDgScanLog selectTDgScanLogById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TDgScanLog> selectTDgScanLogList(Wrapper<TDgScanLog> queryWrapper);

    /** 新增 */
    int insertTDgScanLog(TDgScanLog record);

    /** 修改 */
    int updateTDgScanLog(TDgScanLog record);

    /** 批量删除 */
    int deleteTDgScanLogByIds(String ids);

    /** 按主键删除 */
    int deleteTDgScanLogById(Long id);

    /** 登记流转：0提交 1归档 */
    int advanceTDgScanLog(Long id, Integer action, String remark);
}
