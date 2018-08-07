package io.haitaoc.controller;

import io.haitaoc.model.AppSys;
import io.haitaoc.model.JSONResult;
import io.haitaoc.service.AppSysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "API - AppSysController", description = "应用系统接口")
@RestController
public class AppSysController {

    @Autowired
    private AppSysService appSysService;

    @ApiOperation(value = "查询所有系统信息的接口", notes = "获取所有系统下的设备监控项和业务状况",response = JSONResult.class)
    @GetMapping("/getSysInfo")
    public JSONResult basicInfo(){
        List<AppSys> allSysDeviceItemInfo = appSysService.getAllSysInfo();
        return JSONResult.ok(allSysDeviceItemInfo);
    }
    @ApiOperation(value = "根据Id获取系统详情", notes = "列出id对应的系统信息(设备状况和业务状况)",response = JSONResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysId", value = "系统Id", required = true,
                    dataType = "int", paramType = "path", defaultValue = "1")
    })
    @GetMapping("/getSysDetail/{sysId}")
    public JSONResult sysDetailInfo(@PathVariable int sysId){
        AppSys appSys = appSysService.getSysWithDeviceItems(sysId);
        appSys.setSysId(sysId);
        return JSONResult.ok(appSys);
    }

}
