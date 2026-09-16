package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TDgRule;
import com.fc.v2.service.ITDgRuleService;
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
 * 浅情判定规则 Controller
 *
 * @author fuce
 * @date 2026-09-16
 */
@Api(value = "浅情判定规则")
@Controller
@RequestMapping("/DgRuleController")
public class DgRuleController extends BaseController {

    private final String prefix = "admin/dgRule";

    @Autowired
    private ITDgRuleService dgRuleService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("dredge:dgRule:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "浅情判定规则集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("dredge:dgRule:list")
    @ResponseBody
    public ResultTable list(TDgRule record) {
        QueryWrapper<TDgRule> queryWrapper = new QueryWrapper<TDgRule>();
        queryWrapper.like(StringUtils.isNotEmpty(record.getRuleCode()), "rule_code", record.getRuleCode());
        queryWrapper.like(StringUtils.isNotEmpty(record.getRuleName()), "rule_name", record.getRuleName());
        queryWrapper.eq("del_flag", 0);
        startPage();
        PageInfo<TDgRule> page = new PageInfo<TDgRule>(dgRuleService.selectTDgRuleList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        return prefix + "/add";
    }

    @Log(title = "浅情判定规则新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("dredge:dgRule:add")
    @ResponseBody
    public AjaxResult add(TDgRule record) {
        int rows = dgRuleService.insertTDgRule(record);
        return rows > 0 ? success() : error("保存失败：规则编号重复，或分界线未按从低到高设置");
    }

    @ApiOperation(value = "修改跳转", notes = "修改跳转")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("DgRule", dgRuleService.selectTDgRuleById(id));
        return prefix + "/edit";
    }

    @Log(title = "浅情判定规则修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("dredge:dgRule:edit")
    @ResponseBody
    public AjaxResult editSave(TDgRule record) {
        int rows = dgRuleService.updateTDgRule(record);
        return rows > 0 ? success() : error("保存失败：规则编号重复，或分界线未按从低到高设置");
    }

    @Log(title = "浅情判定规则删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("dredge:dgRule:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dgRuleService.deleteTDgRuleByIds(ids));
    }
}
