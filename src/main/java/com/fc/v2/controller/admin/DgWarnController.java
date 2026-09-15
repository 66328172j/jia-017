package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TDgWarn;
import com.fc.v2.service.ITDgWarnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 浅情预警单 Controller
 *
 * @author fuce
 * @date 2026-09-12
 */
@Api(value = "浅情预警单")
@Controller
@RequestMapping("/DgWarnController")
public class DgWarnController extends BaseController {

    private final String prefix = "admin/dgWarn";

    @Autowired
    private ITDgWarnService dgWarnService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("dredge:dgWarn:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "浅情预警单集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("dredge:dgWarn:list")
    @ResponseBody
    public ResultTable list(TDgWarn record) {
        QueryWrapper<TDgWarn> queryWrapper = new QueryWrapper<TDgWarn>();
        startPage();
        com.github.pagehelper.PageInfo<TDgWarn> page =
                new com.github.pagehelper.PageInfo<TDgWarn>(dgWarnService.selectTDgWarnList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @Log(title = "浅情预警单新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("dredge:dgWarn:add")
    @ResponseBody
    public AjaxResult add(TDgWarn record) {
        return toAjax(dgWarnService.insertTDgWarn(record));
    }

    @Log(title = "浅情预警单修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("dredge:dgWarn:edit")
    @ResponseBody
    public AjaxResult editSave(TDgWarn record) {
        return toAjax(dgWarnService.updateTDgWarn(record));
    }

    @Log(title = "浅情预警单删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("dredge:dgWarn:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dgWarnService.deleteTDgWarnByIds(ids));
    }
}
