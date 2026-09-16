package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TDgPlan;
import com.fc.v2.service.ITDgPlanService;
import com.fc.v2.util.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 疏浚计划单 Controller
 *
 * @author fuce
 * @date 2026-09-12
 */
@Api(value = "疏浚计划单")
@Controller
@RequestMapping("/DgPlanController")
public class DgPlanController extends BaseController {

    private final String prefix = "admin/dgPlan";

    @Autowired
    private ITDgPlanService dgPlanService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("dredge:dgPlan:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "疏浚计划单集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("dredge:dgPlan:list")
    @ResponseBody
    public ResultTable list(TDgPlan record) {
        QueryWrapper<TDgPlan> queryWrapper = new QueryWrapper<TDgPlan>();
        queryWrapper.like(StringUtils.isNotEmpty(record.getDredgeNo()), "dredge_no", record.getDredgeNo());
        queryWrapper.like(StringUtils.isNotEmpty(record.getReachCode()), "reach_code", record.getReachCode());
        queryWrapper.eq("del_flag", 0);
        startPage();
        PageInfo<TDgPlan> page = new PageInfo<TDgPlan>(dgPlanService.selectTDgPlanList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.put("reaches", dgPlanService.selectReachOptions());
        return prefix + "/add";
    }

    @Log(title = "疏浚计划单新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("dredge:dgPlan:add")
    @ResponseBody
    public AjaxResult add(TDgPlan record) {
        int rows = dgPlanService.insertTDgPlan(record);
        return rows > 0 ? success() : error("登记失败：请核对必填项、计划/完成方量与河段档案是否有效");
    }

    @ApiOperation(value = "修改跳转", notes = "修改跳转")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("DgPlan", dgPlanService.selectTDgPlanById(id));
        mmap.put("reaches", dgPlanService.selectReachOptions());
        return prefix + "/edit";
    }

    @Log(title = "疏浚计划单修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("dredge:dgPlan:edit")
    @ResponseBody
    public AjaxResult editSave(TDgPlan record) {
        int rows = dgPlanService.updateTDgPlan(record);
        return rows > 0 ? success() : error("保存失败：计划单已归档，或方量、河段档案无效");
    }

    @Log(title = "疏浚计划单归档", action = "archive")
    @ApiOperation(value = "归档", notes = "归档")
    @PostMapping("/archive")
    @RequiresPermissions("dredge:dgPlan:archive")
    @ResponseBody
    public AjaxResult archive(Long id) {
        int rows = dgPlanService.archiveTDgPlan(id);
        return rows > 0 ? success() : error("归档失败：计划单不存在或已归档");
    }

    @Log(title = "疏浚计划单删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("dredge:dgPlan:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        int rows = dgPlanService.deleteTDgPlanByIds(ids);
        return rows > 0 ? success() : error("删除失败：已归档计划单不允许删除");
    }
}
