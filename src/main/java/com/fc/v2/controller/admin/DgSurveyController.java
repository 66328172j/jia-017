package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TDgSurvey;
import com.fc.v2.service.ITDgSurveyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 断面测深记录 Controller
 *
 * @author fuce
 * @date 2026-09-12
 */
@Api(value = "断面测深记录")
@Controller
@RequestMapping("/DgSurveyController")
public class DgSurveyController extends BaseController {

    private final String prefix = "admin/dgSurvey";

    @Autowired
    private ITDgSurveyService dgSurveyService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("dredge:dgSurvey:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "断面测深记录集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("dredge:dgSurvey:list")
    @ResponseBody
    public ResultTable list(TDgSurvey record) {
        QueryWrapper<TDgSurvey> queryWrapper = new QueryWrapper<TDgSurvey>();
        startPage();
        com.github.pagehelper.PageInfo<TDgSurvey> page =
                new com.github.pagehelper.PageInfo<TDgSurvey>(dgSurveyService.selectTDgSurveyList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @Log(title = "断面测深记录新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("dredge:dgSurvey:add")
    @ResponseBody
    public AjaxResult add(TDgSurvey record) {
        return toAjax(dgSurveyService.insertTDgSurvey(record));
    }

    @Log(title = "断面测深记录修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("dredge:dgSurvey:edit")
    @ResponseBody
    public AjaxResult editSave(TDgSurvey record) {
        return toAjax(dgSurveyService.updateTDgSurvey(record));
    }

    @Log(title = "断面测深记录删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("dredge:dgSurvey:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dgSurveyService.deleteTDgSurveyByIds(ids));
    }
}
