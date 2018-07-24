package io.haitaoc.controller;

import io.haitaoc.model.JSONResult;
import io.haitaoc.model.Warn;
import io.haitaoc.service.WarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class WarnController {

    @Autowired
    WarnService warnService;

    @GetMapping("/getWarnCount")
    public JSONResult warnCount(){
        Map<String,Integer> deviceWarnCount = warnService.deviceWarnCounts();
        return JSONResult.ok(deviceWarnCount);
    }

    @GetMapping("/getUnhandledWarnInfo/{deviceIp}")
    public JSONResult unhandledWarnInfo(@PathVariable String deviceIp){
        List<Warn> IpWarnList = warnService.findIpWarns(deviceIp);
        return JSONResult.ok(IpWarnList);
    }

    @GetMapping("/getHandledWarnInfo/{deviceIp}")
    public JSONResult handledWarnInfo(@PathVariable String deviceIp){
        List<Warn> IpWarnList = warnService.findConfirmedIpWarns(deviceIp);
        return JSONResult.ok(IpWarnList);
    }

    /**
     * 更新warn表中的confirm字段, 同时在sys_device_items表中插入对应的修复记录
     * @param
     * @return
     */
    @PostMapping("/updateConfirm")
    public JSONResult updateWarn(@RequestBody Warn warn){
        System.out.println(warn.getDeviceIP());
        warnService.updateConfirm(warn.getId(),warn.getConfirm());
        return JSONResult.ok();
    }

}
