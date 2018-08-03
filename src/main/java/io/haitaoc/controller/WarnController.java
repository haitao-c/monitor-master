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

    /**以下针对设备硬件监控项的告警处理*/
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


    /*** 更新warn表中的confirm字段, 同时在sys_device_items表中插入对应的修复记录*/
    @PostMapping("/updateConfirm")
    public JSONResult updateWarn(@RequestBody Warn warn){
        //  System.out.println(warn.getDeviceIP());
        warnService.updateConfirm(warn.getId(),warn.getConfirm());
        return JSONResult.ok();
    }

    /**以下针对业务状况监控项的告警处理*/
    @GetMapping("/getBusinessWarnCount")
    public JSONResult businessWarnCount(){
        Map<Integer,Integer> deviceWarnCount = warnService.businessWarnCounts();
        return JSONResult.ok(deviceWarnCount);
    }
    @GetMapping("/getUnhandledBusiness/{sysId}")
    public JSONResult unhandledBusiness(@PathVariable int sysId){
        List<Warn> IdWarnList = warnService.findIdWarns(sysId);
        return JSONResult.ok(IdWarnList);
    }

    @GetMapping("/getHandledBusiness/{sysId}")
    public JSONResult handledBusiness(@PathVariable int sysId){
        List<Warn> IdWarnList = warnService.findConfirmedIdWarns(sysId);
        return JSONResult.ok(IdWarnList);
    }

    /*** 更新warn表中的confirm字段, 同时在sys_device_items表中插入对应的修复记录*/
    @PostMapping("/updateBusinessConfirm")
    public JSONResult updateBusinessWarn(@RequestBody Warn warn){
        warnService.updateBusinessConfirm(warn.getId(),warn.getConfirm());
        return JSONResult.ok();
    }


}
