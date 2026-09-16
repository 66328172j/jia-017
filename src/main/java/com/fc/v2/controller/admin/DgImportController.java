package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TDgImport;
import com.fc.v2.service.ITDgImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 测量数据导入批次 Controller
 *
 * @author fuce
 * @date 2026-09-12
 */
@Api(value = "测量数据导入批次")
@Controller
@RequestMapping("/DgImportController")
public class DgImportController extends BaseController {

    private final String prefix = "admin/dgImport";

    @Autowired
    private ITDgImportService dgImportService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("dredge:dgImport:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "测量数据导入批次集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("dredge:dgImport:list")
    @ResponseBody
    public ResultTable list(TDgImport record) {
        QueryWrapper<TDgImport> queryWrapper = new QueryWrapper<TDgImport>();
        startPage();
        com.github.pagehelper.PageInfo<TDgImport> page =
                new com.github.pagehelper.PageInfo<TDgImport>(dgImportService.selectTDgImportList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @Log(title = "测量数据导入批次新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("dredge:dgImport:add")
    @ResponseBody
    public AjaxResult add(TDgImport record) {
        return toAjax(dgImportService.insertTDgImport(record));
    }

    @Log(title = "测量数据导入批次修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("dredge:dgImport:edit")
    @ResponseBody
    public AjaxResult editSave(TDgImport record) {
        return toAjax(dgImportService.updateTDgImport(record));
    }

    @Log(title = "测量数据导入批次删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("dredge:dgImport:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dgImportService.deleteTDgImportByIds(ids));
    }
}
