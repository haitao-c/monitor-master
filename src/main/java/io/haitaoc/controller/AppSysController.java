package io.haitaoc.controller;

import io.haitaoc.model.AppSys;
import io.haitaoc.model.JSONResult;
import io.haitaoc.service.AppSysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AppSysController {

    @Autowired
    private AppSysService appSysService;

    @GetMapping("/getSysInfo")
    public JSONResult basicInfo(){
        List<AppSys> allSysDeviceItemInfo = appSysService.getAllSysInfo();
        return JSONResult.ok(allSysDeviceItemInfo);
    }

    @GetMapping("/getSysDetail/{sysId}")
    public JSONResult sysDetailInfo(@PathVariable int sysId){
        AppSys appSys = appSysService.getSysWithDeviceItems(sysId);
        appSys.setSysId(sysId);
        return JSONResult.ok(appSys);
    }

}
