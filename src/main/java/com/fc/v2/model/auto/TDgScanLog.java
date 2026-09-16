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
import java.util.Date;

/**
 * 移动端现场测深登记对象 t_dg_scan_log
 *
 * @author fuce
 * @date 2026-09-12
 */
@TableName("t_dg_scan_log")
@ApiModel(value = "TDgScanLog", description = "移动端现场测深登记")
public class TDgScanLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /** 登记流水号 */
    @TableField("scan_no")
    @ApiModelProperty(value = "登记流水号")
    private String scanNo;

    /** 河段ID */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("reach_id")
    @ApiModelProperty(value = "河段ID")
    private Long reachId;

    /** 河段编号(冗余，以档案为准) */
    @TableField("reach_code")
    @ApiModelProperty(value = "河段编号(冗余，以档案为准)")
    private String reachCode;

    /** 河段名称(冗余，以档案为准) */
    @TableField("reach_name")
    @ApiModelProperty(value = "河段名称(冗余，以档案为准)")
    private String reachName;

    /** 关联测量单号 */
    @TableField("survey_ref_no")
    @ApiModelProperty(value = "关联测量单号")
    private String surveyRefNo;

    /** 登记类型 1现场查询 2现场登记 */
    @TableField("scan_type")
    @ApiModelProperty(value = "登记类型 1现场查询 2现场登记")
    private Integer scanType;

    /** 登记时间 */
    @TableField("scan_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "登记时间")
    private Date scanTime;

    /** 登记有效期至 */
    @TableField("valid_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "登记有效期至")
    private Date validDate;

    /** 有效期剩余天数 */
    @TableField("valid_days")
    @ApiModelProperty(value = "有效期剩余天数")
    private Integer validDays;

    /** 登记状态 0暂存 1已提交 2已归档 */
    @TableField("scan_status")
    @ApiModelProperty(value = "登记状态 0暂存 1已提交 2已归档")
    private Integer scanStatus;

    /** 登记人 */
    @TableField("scan_by")
    @ApiModelProperty(value = "登记人")
    private String scanBy;

    /** 登记设备 */
    @TableField("device")
    @ApiModelProperty(value = "登记设备")
    private String device;

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

    public String getScanNo() {
        return scanNo;
    }

    public void setScanNo(String scanNo) {
        this.scanNo = scanNo;
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

    public String getReachName() {
        return reachName;
    }

    public void setReachName(String reachName) {
        this.reachName = reachName;
    }

    public String getSurveyRefNo() {
        return surveyRefNo;
    }

    public void setSurveyRefNo(String surveyRefNo) {
        this.surveyRefNo = surveyRefNo;
    }

    public Integer getScanType() {
        return scanType;
    }

    public void setScanType(Integer scanType) {
        this.scanType = scanType;
    }

    public Date getScanTime() {
        return scanTime;
    }

    public void setScanTime(Date scanTime) {
        this.scanTime = scanTime;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public Integer getScanStatus() {
        return scanStatus;
    }

    public void setScanStatus(Integer scanStatus) {
        this.scanStatus = scanStatus;
    }

    public String getScanBy() {
        return scanBy;
    }

    public void setScanBy(String scanBy) {
        this.scanBy = scanBy;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
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
