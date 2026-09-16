package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TDgReach;
import com.fc.v2.model.auto.TDgRule;
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

    /** 河段下拉选项（未删除档案，登记页使用） */
    List<TDgReach> selectReachOptions();

    /** 判定规则下拉选项（未删除档案，登记页使用） */
    List<TDgRule> selectRuleOptions();

    /** 新增 */
    int insertTDgSurvey(TDgSurvey record);

    /** 修改（仅待复核记录可改，浅情等级由系统重算） */
    int updateTDgSurvey(TDgSurvey record);

    /** 复核（待复核 -> 已复核，已复核后不可再改） */
    int reviewTDgSurvey(Long id);

    /** 批量删除（已复核记录不允许删除） */
    int deleteTDgSurveyByIds(String ids);

    /** 按主键删除 */
    int deleteTDgSurveyById(Long id);
}
