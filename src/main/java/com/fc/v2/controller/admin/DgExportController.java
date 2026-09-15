package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TDgExport;
import com.fc.v2.service.ITDgExportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 测量台账导出记录 Controller
 *
 * @author fuce
 * @date 2026-09-12
 */
@Api(value = "测量台账导出记录")
@Controller
@RequestMapping("/DgExportController")
public class DgExportController extends BaseController {

    private final String prefix = "admin/dgExport";

    @Autowired
    private ITDgExportService dgExportService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("dredge:dgExport:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "测量台账导出记录集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("dredge:dgExport:list")
    @ResponseBody
    public ResultTable list(TDgExport record) {
        QueryWrapper<TDgExport> queryWrapper = new QueryWrapper<TDgExport>();
        startPage();
        com.github.pagehelper.PageInfo<TDgExport> page =
                new com.github.pagehelper.PageInfo<TDgExport>(dgExportService.selectTDgExportList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @Log(title = "测量台账导出记录新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("dredge:dgExport:add")
    @ResponseBody
    public AjaxResult add(TDgExport record) {
        return toAjax(dgExportService.insertTDgExport(record));
    }

    @Log(title = "测量台账导出记录修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("dredge:dgExport:edit")
    @ResponseBody
    public AjaxResult editSave(TDgExport record) {
        return toAjax(dgExportService.updateTDgExport(record));
    }

    @Log(title = "测量台账导出记录删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("dredge:dgExport:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dgExportService.deleteTDgExportByIds(ids));
    }
}
