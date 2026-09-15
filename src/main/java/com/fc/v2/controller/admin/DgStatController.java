package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TDgStat;
import com.fc.v2.service.ITDgStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 疏浚质量合格率统计 Controller
 *
 * @author fuce
 * @date 2026-09-12
 */
@Api(value = "疏浚质量合格率统计")
@Controller
@RequestMapping("/DgStatController")
public class DgStatController extends BaseController {

    private final String prefix = "admin/dgStat";

    @Autowired
    private ITDgStatService dgStatService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("dredge:dgStat:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "疏浚质量合格率统计集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("dredge:dgStat:list")
    @ResponseBody
    public ResultTable list(TDgStat record) {
        QueryWrapper<TDgStat> queryWrapper = new QueryWrapper<TDgStat>();
        startPage();
        com.github.pagehelper.PageInfo<TDgStat> page =
                new com.github.pagehelper.PageInfo<TDgStat>(dgStatService.selectTDgStatList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @Log(title = "疏浚质量合格率统计新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("dredge:dgStat:add")
    @ResponseBody
    public AjaxResult add(TDgStat record) {
        return toAjax(dgStatService.insertTDgStat(record));
    }

    @Log(title = "疏浚质量合格率统计修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("dredge:dgStat:edit")
    @ResponseBody
    public AjaxResult editSave(TDgStat record) {
        return toAjax(dgStatService.updateTDgStat(record));
    }

    @Log(title = "疏浚质量合格率统计删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("dredge:dgStat:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dgStatService.deleteTDgStatByIds(ids));
    }
}
