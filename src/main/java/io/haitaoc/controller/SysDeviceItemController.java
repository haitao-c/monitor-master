package io.haitaoc.controller;

import io.haitaoc.model.JSONResult;
import io.haitaoc.model.SysDeviceItems;
import io.haitaoc.service.SysDeviceItemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "API - SysDeviceItemController", description = "系统设备状况接口")
@RestController
public class SysDeviceItemController {

    @Autowired
    private SysDeviceItemsService sysDeviceItemsService;

    @ApiOperation(value = "获取设备状况记录详情", notes = "根据设备IP以及分页信息获取设备状况",response = JSONResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_ip", value = "设备IP", required = true,
                    dataType = "string", paramType = "path", defaultValue = "127.0.0.1"),
            @ApiImplicitParam(name = "page", value = "当前页数", required = true,
                    dataType = "int", paramType = "path", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "展示条数", required = true,
                    dataType = "int", paramType = "path", defaultValue = "5"),
    })
    @GetMapping("/getDeviceItemsByIp/{device_ip}/{page}/{pageSize}")
    public JSONResult sysDetailInfoByIp(@PathVariable String device_ip,@PathVariable Integer page,@PathVariable Integer pageSize){
        List<SysDeviceItems> sysDeviceItems = sysDeviceItemsService.getDeviceItemsByIp(device_ip,page,pageSize);
        return JSONResult.ok(sysDeviceItems);
    }

    @ApiOperation(value = "获取设备详情总条数", notes = "根据设备IP获取记录总数",response = JSONResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_ip", value = "设备IP", required = true,
                    dataType = "string", paramType = "path", defaultValue = "127.0.0.1"),
    })
    @GetMapping("/getDeviceItemsByIp/{device_ip}/total")
    public JSONResult getRecordsNumber(@PathVariable String device_ip){
        int total = sysDeviceItemsService.recordsNumber(device_ip);
        return JSONResult.ok(total);
    }

}
