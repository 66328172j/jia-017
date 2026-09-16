package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TDgMaterial;
import com.fc.v2.service.ITDgMaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 疏浚物料台账 Controller
 *
 * @author fuce
 * @date 2026-09-12
 */
@Api(value = "疏浚物料台账")
@Controller
@RequestMapping("/DgMaterialController")
public class DgMaterialController extends BaseController {

    private final String prefix = "admin/dgMaterial";

    @Autowired
    private ITDgMaterialService dgMaterialService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("dredge:dgMaterial:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "疏浚物料台账集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("dredge:dgMaterial:list")
    @ResponseBody
    public ResultTable list(TDgMaterial record) {
        QueryWrapper<TDgMaterial> queryWrapper = new QueryWrapper<TDgMaterial>();
        startPage();
        com.github.pagehelper.PageInfo<TDgMaterial> page =
                new com.github.pagehelper.PageInfo<TDgMaterial>(dgMaterialService.selectTDgMaterialList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @Log(title = "疏浚物料台账新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("dredge:dgMaterial:add")
    @ResponseBody
    public AjaxResult add(TDgMaterial record) {
        return toAjax(dgMaterialService.insertTDgMaterial(record));
    }

    @Log(title = "疏浚物料台账修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("dredge:dgMaterial:edit")
    @ResponseBody
    public AjaxResult editSave(TDgMaterial record) {
        return toAjax(dgMaterialService.updateTDgMaterial(record));
    }

    @Log(title = "疏浚物料台账删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("dredge:dgMaterial:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dgMaterialService.deleteTDgMaterialByIds(ids));
    }
}
