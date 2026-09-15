package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TDgScanLog;
import com.fc.v2.service.ITDgScanLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 移动端现场测深登记 Controller
 *
 * @author fuce
 * @date 2026-09-12
 */
@Api(value = "移动端现场测深登记")
@Controller
@RequestMapping("/DgScanLogController")
public class DgScanLogController extends BaseController {

    private final String prefix = "admin/dgScanLog";

    @Autowired
    private ITDgScanLogService dgScanLogService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("dredge:dgScanLog:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "移动端现场测深登记集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("dredge:dgScanLog:list")
    @ResponseBody
    public ResultTable list(TDgScanLog record) {
        QueryWrapper<TDgScanLog> queryWrapper = new QueryWrapper<TDgScanLog>();
        startPage();
        com.github.pagehelper.PageInfo<TDgScanLog> page =
                new com.github.pagehelper.PageInfo<TDgScanLog>(dgScanLogService.selectTDgScanLogList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @Log(title = "移动端现场测深登记新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("dredge:dgScanLog:add")
    @ResponseBody
    public AjaxResult add(TDgScanLog record) {
        return toAjax(dgScanLogService.insertTDgScanLog(record));
    }

    @Log(title = "移动端现场测深登记修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("dredge:dgScanLog:edit")
    @ResponseBody
    public AjaxResult editSave(TDgScanLog record) {
        return toAjax(dgScanLogService.updateTDgScanLog(record));
    }

    @Log(title = "移动端现场测深登记删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("dredge:dgScanLog:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dgScanLogService.deleteTDgScanLogByIds(ids));
    }
}
