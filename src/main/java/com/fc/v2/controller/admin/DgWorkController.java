package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TDgWork;
import com.fc.v2.service.ITDgWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 疏浚作业单 Controller
 *
 * @author fuce
 * @date 2026-09-12
 */
@Api(value = "疏浚作业单")
@Controller
@RequestMapping("/DgWorkController")
public class DgWorkController extends BaseController {

    private final String prefix = "admin/dgWork";

    @Autowired
    private ITDgWorkService dgWorkService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("dredge:dgWork:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "疏浚作业单集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("dredge:dgWork:list")
    @ResponseBody
    public ResultTable list(TDgWork record) {
        QueryWrapper<TDgWork> queryWrapper = new QueryWrapper<TDgWork>();
        startPage();
        com.github.pagehelper.PageInfo<TDgWork> page =
                new com.github.pagehelper.PageInfo<TDgWork>(dgWorkService.selectTDgWorkList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @Log(title = "疏浚作业单新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("dredge:dgWork:add")
    @ResponseBody
    public AjaxResult add(TDgWork record) {
        return toAjax(dgWorkService.insertTDgWork(record));
    }

    @Log(title = "疏浚作业单修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("dredge:dgWork:edit")
    @ResponseBody
    public AjaxResult editSave(TDgWork record) {
        return toAjax(dgWorkService.updateTDgWork(record));
    }

    @Log(title = "疏浚作业单删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("dredge:dgWork:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dgWorkService.deleteTDgWorkByIds(ids));
    }
}
