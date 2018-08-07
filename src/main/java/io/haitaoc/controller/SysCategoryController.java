package io.haitaoc.controller;

import io.haitaoc.model.JSONResult;
import io.haitaoc.model.SysCategory;
import io.haitaoc.service.SysCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "API - SysCategoryController", description = "系统分类接口")
@RestController
public class SysCategoryController {

    @Autowired
    private SysCategoryService sysCategoryService;

    @ApiOperation(value = "获取侧边菜单", notes = "列出侧边栏分类",response = JSONResult.class)
    @GetMapping("/getAsideList")
    public JSONResult classify(){
        List<SysCategory>  sysCategoryWithSys = sysCategoryService.allCategoryWithSys();
        return JSONResult.ok(sysCategoryWithSys);
    }

    @ApiOperation(value = "获取所有系统信息", notes = "根据分类获取系统信息(包含了设备状况, 没有包含业务状况)",response = JSONResult.class)
    @GetMapping("/getAllInfo")
    public JSONResult allSysAndItemInfo(){
        List<SysCategory>  sysCategoryWithSysAndDeviceItem = sysCategoryService.allCategoryWithSysAndDevice();
        return JSONResult.ok(sysCategoryWithSysAndDeviceItem);
    }
}
