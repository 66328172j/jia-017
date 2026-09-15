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
 * 疏浚计划单对象 t_dg_plan
 *
 * @author fuce
 * @date 2026-09-12
 */
@TableName("t_dg_plan")
@ApiModel(value = "TDgPlan", description = "疏浚计划单")
public class TDgPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /** 计划单号 */
    @TableField("dredge_no")
    @ApiModelProperty(value = "计划单号")
    private String dredgeNo;

    /** 河段ID */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("reach_id")
    @ApiModelProperty(value = "河段ID")
    private Long reachId;

    /** 河段编号(冗余，以档案为准) */
    @TableField("reach_code")
    @ApiModelProperty(value = "河段编号(冗余，以档案为准)")
    private String reachCode;

    /** 计划月份 */
    @TableField("plan_month")
    @ApiModelProperty(value = "计划月份")
    private String planMonth;

    /** 计划方量(m³) */
    @TableField("plan_cube")
    @ApiModelProperty(value = "计划方量(m³)")
    private Integer planCube;

    /** 完成方量(m³) */
    @TableField("done_cube")
    @ApiModelProperty(value = "完成方量(m³)")
    private Integer doneCube;

    /** 完成率(%) */
    @TableField("done_rate")
    @ApiModelProperty(value = "完成率(%)")
    private BigDecimal doneRate;

    /** 编制人 */
    @TableField("plan_by")
    @ApiModelProperty(value = "编制人")
    private String planBy;

    /** 计划状态 0待确认 1已归档 */
    @TableField("plan_status")
    @ApiModelProperty(value = "计划状态 0待确认 1已归档")
    private Integer planStatus;

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

    public String getDredgeNo() {
        return dredgeNo;
    }

    public void setDredgeNo(String dredgeNo) {
        this.dredgeNo = dredgeNo;
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

    public String getPlanMonth() {
        return planMonth;
    }

    public void setPlanMonth(String planMonth) {
        this.planMonth = planMonth;
    }

    public Integer getPlanCube() {
        return planCube;
    }

    public void setPlanCube(Integer planCube) {
        this.planCube = planCube;
    }

    public Integer getDoneCube() {
        return doneCube;
    }

    public void setDoneCube(Integer doneCube) {
        this.doneCube = doneCube;
    }

    public BigDecimal getDoneRate() {
        return doneRate;
    }

    public void setDoneRate(BigDecimal doneRate) {
        this.doneRate = doneRate;
    }

    public String getPlanBy() {
        return planBy;
    }

    public void setPlanBy(String planBy) {
        this.planBy = planBy;
    }

    public Integer getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(Integer planStatus) {
        this.planStatus = planStatus;
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
