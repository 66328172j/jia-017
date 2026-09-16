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
 * 回淤监测记录对象 t_dg_silt
 *
 * @author fuce
 * @date 2026-09-12
 */
@TableName("t_dg_silt")
@ApiModel(value = "TDgSilt", description = "回淤监测记录")
public class TDgSilt implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /** 监测单号 */
    @TableField("silt_no")
    @ApiModelProperty(value = "监测单号")
    private String siltNo;

    /** 河段ID */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("reach_id")
    @ApiModelProperty(value = "河段ID")
    private Long reachId;

    /** 河段编号(冗余，以档案为准) */
    @TableField("reach_code")
    @ApiModelProperty(value = "河段编号(冗余，以档案为准)")
    private String reachCode;

    /** 回淤厚度(m) */
    @TableField("silt_depth")
    @ApiModelProperty(value = "回淤厚度(m)")
    private BigDecimal siltDepth;

    /** 复测间隔(天) */
    @TableField("interval_days")
    @ApiModelProperty(value = "复测间隔(天)")
    private Integer intervalDays;

    /** 监测日期 */
    @TableField("monitor_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "监测日期")
    private Date monitorDate;

    /** 下次复测日期 */
    @TableField("next_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "下次复测日期")
    private Date nextDate;

    /** 距下次复测剩余天数 */
    @TableField("remain_days")
    @ApiModelProperty(value = "距下次复测剩余天数")
    private Integer remainDays;

    /** 监测人 */
    @TableField("monitor_by")
    @ApiModelProperty(value = "监测人")
    private String monitorBy;

    /** 处置进度 0待复核 1已复核 2已归档 */
    @TableField("silt_status")
    @ApiModelProperty(value = "处置进度 0待复核 1已复核 2已归档")
    private Integer siltStatus;

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

    public String getSiltNo() {
        return siltNo;
    }

    public void setSiltNo(String siltNo) {
        this.siltNo = siltNo;
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

    public BigDecimal getSiltDepth() {
        return siltDepth;
    }

    public void setSiltDepth(BigDecimal siltDepth) {
        this.siltDepth = siltDepth;
    }

    public Integer getIntervalDays() {
        return intervalDays;
    }

    public void setIntervalDays(Integer intervalDays) {
        this.intervalDays = intervalDays;
    }

    public Date getMonitorDate() {
        return monitorDate;
    }

    public void setMonitorDate(Date monitorDate) {
        this.monitorDate = monitorDate;
    }

    public Date getNextDate() {
        return nextDate;
    }

    public void setNextDate(Date nextDate) {
        this.nextDate = nextDate;
    }

    public Integer getRemainDays() {
        return remainDays;
    }

    public void setRemainDays(Integer remainDays) {
        this.remainDays = remainDays;
    }

    public String getMonitorBy() {
        return monitorBy;
    }

    public void setMonitorBy(String monitorBy) {
        this.monitorBy = monitorBy;
    }

    public Integer getSiltStatus() {
        return siltStatus;
    }

    public void setSiltStatus(Integer siltStatus) {
        this.siltStatus = siltStatus;
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
