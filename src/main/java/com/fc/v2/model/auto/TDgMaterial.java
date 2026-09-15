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
 * 疏浚物料台账对象 t_dg_material
 *
 * @author fuce
 * @date 2026-09-12
 */
@TableName("t_dg_material")
@ApiModel(value = "TDgMaterial", description = "疏浚物料台账")
public class TDgMaterial implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /** 台账单号 */
    @TableField("material_no")
    @ApiModelProperty(value = "台账单号")
    private String materialNo;

    /** 关联预警单ID */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("warn_id")
    @ApiModelProperty(value = "关联预警单ID")
    private Long warnId;

    /** 预警单号(冗余，以预警单为准) */
    @TableField("warn_no")
    @ApiModelProperty(value = "预警单号(冗余，以预警单为准)")
    private String warnNo;

    /** 物料名称 */
    @TableField("material_name")
    @ApiModelProperty(value = "物料名称")
    private String materialName;

    /** 规格型号 */
    @TableField("spec")
    @ApiModelProperty(value = "规格型号")
    private String spec;

    /** 入库数量 */
    @TableField("in_qty")
    @ApiModelProperty(value = "入库数量")
    private Integer inQty;

    /** 消耗数量 */
    @TableField("use_qty")
    @ApiModelProperty(value = "消耗数量")
    private Integer useQty;

    /** 结存数量 */
    @TableField("remain_qty")
    @ApiModelProperty(value = "结存数量")
    private Integer remainQty;

    /** 保管员 */
    @TableField("keeper")
    @ApiModelProperty(value = "保管员")
    private String keeper;

    /** 台账状态 0在用 1已用尽 */
    @TableField("material_status")
    @ApiModelProperty(value = "台账状态 0在用 1已用尽")
    private Integer materialStatus;

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

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public Long getWarnId() {
        return warnId;
    }

    public void setWarnId(Long warnId) {
        this.warnId = warnId;
    }

    public String getWarnNo() {
        return warnNo;
    }

    public void setWarnNo(String warnNo) {
        this.warnNo = warnNo;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getInQty() {
        return inQty;
    }

    public void setInQty(Integer inQty) {
        this.inQty = inQty;
    }

    public Integer getUseQty() {
        return useQty;
    }

    public void setUseQty(Integer useQty) {
        this.useQty = useQty;
    }

    public Integer getRemainQty() {
        return remainQty;
    }

    public void setRemainQty(Integer remainQty) {
        this.remainQty = remainQty;
    }

    public String getKeeper() {
        return keeper;
    }

    public void setKeeper(String keeper) {
        this.keeper = keeper;
    }

    public Integer getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(Integer materialStatus) {
        this.materialStatus = materialStatus;
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
