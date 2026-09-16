package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TDgSurvey;
import com.fc.v2.service.ITDgSurveyService;
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
        queryWrapper.like(StringUtils.isNotEmpty(record.getSurveyNo()), "survey_no", record.getSurveyNo());
        queryWrapper.like(StringUtils.isNotEmpty(record.getReachCode()), "reach_code", record.getReachCode());
        queryWrapper.eq("del_flag", 0);
        startPage();
        PageInfo<TDgSurvey> page = new PageInfo<TDgSurvey>(dgSurveyService.selectTDgSurveyList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.put("reaches", dgSurveyService.selectReachOptions());
        modelMap.put("rules", dgSurveyService.selectRuleOptions());
        return prefix + "/add";
    }

    @Log(title = "断面测深记录新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("dredge:dgSurvey:add")
    @ResponseBody
    public AjaxResult add(TDgSurvey record) {
        int rows = dgSurveyService.insertTDgSurvey(record);
        return rows > 0 ? success() : error("登记失败：请核对必填项、河段档案与判定规则是否有效");
    }

    @ApiOperation(value = "修改跳转", notes = "修改跳转")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("DgSurvey", dgSurveyService.selectTDgSurveyById(id));
        mmap.put("reaches", dgSurveyService.selectReachOptions());
        mmap.put("rules", dgSurveyService.selectRuleOptions());
        return prefix + "/edit";
    }

    @Log(title = "断面测深记录修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("dredge:dgSurvey:edit")
    @ResponseBody
    public AjaxResult editSave(TDgSurvey record) {
        int rows = dgSurveyService.updateTDgSurvey(record);
        return rows > 0 ? success() : error("保存失败：记录已复核，或河段、判定规则无效");
    }

    @Log(title = "断面测深记录复核", action = "review")
    @ApiOperation(value = "复核", notes = "复核")
    @PostMapping("/review")
    @RequiresPermissions("dredge:dgSurvey:review")
    @ResponseBody
    public AjaxResult review(Long id) {
        int rows = dgSurveyService.reviewTDgSurvey(id);
        return rows > 0 ? success() : error("复核失败：记录不存在或已复核");
    }

    @Log(title = "断面测深记录删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("dredge:dgSurvey:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        int rows = dgSurveyService.deleteTDgSurveyByIds(ids);
        return rows > 0 ? success() : error("删除失败：已复核记录不允许删除");
    }
}
