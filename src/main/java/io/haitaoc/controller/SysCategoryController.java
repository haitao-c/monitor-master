package io.haitaoc.controller;

import io.haitaoc.model.JSONResult;
import io.haitaoc.model.SysCategory;
import io.haitaoc.service.SysCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysCategoryController {

    @Autowired
    private SysCategoryService sysCategoryService;

    @GetMapping("/getAsideList")
    public JSONResult classify(){
        List<SysCategory>  sysCategoryWithSys = sysCategoryService.allCategoryWithSys();
        return JSONResult.ok(sysCategoryWithSys);
    }

    @GetMapping("/getAllInfo")
    public JSONResult allSysAndItemInfo(){
        List<SysCategory>  sysCategoryWithSysAndDeviceItem = sysCategoryService.allCategoryWithSysAndDevice();
        return JSONResult.ok(sysCategoryWithSysAndDeviceItem);
    }
}
