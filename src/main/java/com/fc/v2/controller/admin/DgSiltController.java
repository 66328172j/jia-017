package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TDgSilt;
import com.fc.v2.service.ITDgSiltService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 回淤监测记录 Controller
 *
 * @author fuce
 * @date 2026-09-12
 */
@Api(value = "回淤监测记录")
@Controller
@RequestMapping("/DgSiltController")
public class DgSiltController extends BaseController {

    private final String prefix = "admin/dgSilt";

    @Autowired
    private ITDgSiltService dgSiltService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("dredge:dgSilt:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "回淤监测记录集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("dredge:dgSilt:list")
    @ResponseBody
    public ResultTable list(TDgSilt record) {
        QueryWrapper<TDgSilt> queryWrapper = new QueryWrapper<TDgSilt>();
        startPage();
        com.github.pagehelper.PageInfo<TDgSilt> page =
                new com.github.pagehelper.PageInfo<TDgSilt>(dgSiltService.selectTDgSiltList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @Log(title = "回淤监测记录新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("dredge:dgSilt:add")
    @ResponseBody
    public AjaxResult add(TDgSilt record) {
        return toAjax(dgSiltService.insertTDgSilt(record));
    }

    @Log(title = "回淤监测记录修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("dredge:dgSilt:edit")
    @ResponseBody
    public AjaxResult editSave(TDgSilt record) {
        return toAjax(dgSiltService.updateTDgSilt(record));
    }

    @Log(title = "回淤监测记录删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("dredge:dgSilt:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dgSiltService.deleteTDgSiltByIds(ids));
    }
}
