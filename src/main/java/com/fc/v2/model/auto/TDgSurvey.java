package com.fc.v2.model.auto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 断面测深记录对象 t_dg_survey
 *
 * @author fuce
 * @date 2026-09-12
 */
@TableName("t_dg_survey")
@ApiModel(value = "TDgSurvey", description = "断面测深记录")
public class TDgSurvey implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /** 测量单号 */
    @TableField("survey_no")
    @ApiModelProperty(value = "测量单号")
    private String surveyNo;

    /** 河段ID */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("reach_id")
    @ApiModelProperty(value = "河段ID")
    private Long reachId;

    /** 河段编号(冗余，以档案为准) */
    @TableField("reach_code")
    @ApiModelProperty(value = "河段编号(冗余，以档案为准)")
    private String reachCode;

    /** 判定规则ID */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("rule_id")
    @ApiModelProperty(value = "判定规则ID")
    private Long ruleId;

    /** 规则编号(冗余，以档案为准) */
    @TableField("rule_code")
    @ApiModelProperty(value = "规则编号(冗余，以档案为准)")
    private String ruleCode;

    /** 浅点率(%) */
    @TableField("shoal_rate")
    @ApiModelProperty(value = "浅点率(%)")
    private BigDecimal shoalRate;

    /** 浅情等级 1良好 2一般 3紧张 4告急 */
    @TableField("shoal_level")
    @ApiModelProperty(value = "浅情等级 1良好 2一般 3紧张 4告急")
    private Integer shoalLevel;

    /** 测量日期 */
    @TableField("survey_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "测量日期")
    private Date surveyDate;

    /** 测量人 */
    @TableField("survey_by")
    @ApiModelProperty(value = "测量人")
    private String surveyBy;

    /** 复核状态 0待复核 1已复核 */
    @TableField("survey_status")
    @ApiModelProperty(value = "复核状态 0待复核 1已复核")
    private Integer surveyStatus;

    /** 删除标记 0正常 1删除 */
    @TableField("del_flag")
    @ApiModelProperty(value = "删除标记 0正常 1删除")
    private Integer delFlag;

    /** 创建者 */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建者")
    private String createBy;

    /** 创建时间 */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /** 更新者 */
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    /** 更新时间 */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /** 备注 */
    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public Long getReachId() {
        return reachId;
    }

    public void setReachId(Long reachId) {
        this.reachId = reachId;
    }

    public String getReachCode() {
        return reachCode;
    }

    public void setReachCode(String reachCode) {
        this.reachCode = reachCode;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public BigDecimal getShoalRate() {
        return shoalRate;
    }

    public void setShoalRate(BigDecimal shoalRate) {
        this.shoalRate = shoalRate;
    }

    public Integer getShoalLevel() {
        return shoalLevel;
    }

    public void setShoalLevel(Integer shoalLevel) {
        this.shoalLevel = shoalLevel;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }

    public String getSurveyBy() {
        return surveyBy;
    }

    public void setSurveyBy(String surveyBy) {
        this.surveyBy = surveyBy;
    }

    public Integer getSurveyStatus() {
        return surveyStatus;
    }

    public void setSurveyStatus(Integer surveyStatus) {
        this.surveyStatus = surveyStatus;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
