package io.haitaoc.controller;

import io.haitaoc.model.JSONResult;
import io.haitaoc.model.Warn;
import io.haitaoc.service.WarnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "API - WarnController", description = "告警状况接口")
@RestController
public class WarnController {

    @Autowired
    WarnService warnService;

    @ApiOperation(value = "获取设备告警总条数", notes = "获取设备告警总条数",response = JSONResult.class)
    /**以下针对设备硬件监控项的告警处理*/
    @GetMapping("/getWarnCount")
    public JSONResult warnCount(){
        Map<String,Integer> deviceWarnCount = warnService.deviceWarnCounts();
        return JSONResult.ok(deviceWarnCount);
    }

    @ApiOperation(value = "获取未处理设备告警", notes = "根据设备IP获取未处理告警",response = JSONResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_ip", value = "设备IP", required = true,
                    dataType = "string", paramType = "path", defaultValue = "127.0.0.1"),
    })
    @GetMapping("/getUnhandledWarnInfo/{deviceIp}")
    public JSONResult unhandledWarnInfo(@PathVariable String deviceIp){
        List<Warn> IpWarnList = warnService.findIpWarns(deviceIp);
        return JSONResult.ok(IpWarnList);
    }

    @ApiOperation(value = "获取已处理设备告警", notes = "根据设备IP获取已处理告警",response = JSONResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_ip", value = "设备IP", required = true,
                    dataType = "string", paramType = "path", defaultValue = "127.0.0.1"),
    })
    @GetMapping("/getHandledWarnInfo/{deviceIp}")
    public JSONResult handledWarnInfo(@PathVariable String deviceIp){
        List<Warn> IpWarnList = warnService.findConfirmedIpWarns(deviceIp);
        return JSONResult.ok(IpWarnList);
    }

    @ApiOperation(value = "更新确认信息", notes = "对处理的告警更新确认信息, 设备表中插入新的正常记录, warn表对应记录更新update_time",response = JSONResult.class)
    /*** 更新warn表中的confirm字段, 同时在sys_device_items表中插入对应的修复记录*/
    @PostMapping("/updateConfirm")
    public JSONResult updateWarn(@RequestBody Warn warn){
        //  System.out.println(warn.getDeviceIP());
        warnService.updateConfirm(warn.getId(),warn.getConfirm());
        return JSONResult.ok();
    }

    @ApiOperation(value = "获取业务告警总条数", notes = "获取业务告警总条数",response = JSONResult.class)
    /**以下针对业务状况监控项的告警处理*/
    @GetMapping("/getBusinessWarnCount")
    public JSONResult businessWarnCount(){
        Map<Integer,Integer> deviceWarnCount = warnService.businessWarnCounts();
        return JSONResult.ok(deviceWarnCount);
    }

    @ApiOperation(value = "获取未处理设备告警", notes = "根据系统id获取未处理告警",response = JSONResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysId", value = "系统id", required = true,
                    dataType = "int", paramType = "path", defaultValue = "1"),
    })
    @GetMapping("/getUnhandledBusiness/{sysId}")
    public JSONResult unhandledBusiness(@PathVariable int sysId){
        List<Warn> IdWarnList = warnService.findIdWarns(sysId);
        return JSONResult.ok(IdWarnList);
    }

    @ApiOperation(value = "获取已处理设备告警", notes = "根据系统id获取已处理告警",response = JSONResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysId", value = "系统id", required = true,
                    dataType = "int", paramType = "path", defaultValue = "1"),
    })
    @GetMapping("/getHandledBusiness/{sysId}")
    public JSONResult handledBusiness(@PathVariable int sysId){
        List<Warn> IdWarnList = warnService.findConfirmedIdWarns(sysId);
        return JSONResult.ok(IdWarnList);
    }

    @ApiOperation(value = "更新确认信息", notes = "对处理的告警更新确认信息, 业务表中插入新的正常记录, warn表对应记录更新update_time",response = JSONResult.class)
    /*** 更新warn表中的confirm字段, 同时在sys_device_items表中插入对应的修复记录*/
    @PostMapping("/updateBusinessConfirm")
    public JSONResult updateBusinessWarn(@RequestBody Warn warn){
        warnService.updateBusinessConfirm(warn.getId(),warn.getConfirm());
        return JSONResult.ok();
    }


}
