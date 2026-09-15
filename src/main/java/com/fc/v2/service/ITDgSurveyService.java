package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TDgSurvey;

import java.util.List;

/**
 * 断面测深记录 Service接口
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface ITDgSurveyService {

    /** 按主键查询 */
    TDgSurvey selectTDgSurveyById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TDgSurvey> selectTDgSurveyList(Wrapper<TDgSurvey> queryWrapper);

    /** 新增 */
    int insertTDgSurvey(TDgSurvey record);

    /** 修改 */
    int updateTDgSurvey(TDgSurvey record);

    /** 批量删除 */
    int deleteTDgSurveyByIds(String ids);

    /** 按主键删除 */
    int deleteTDgSurveyById(Long id);
}
