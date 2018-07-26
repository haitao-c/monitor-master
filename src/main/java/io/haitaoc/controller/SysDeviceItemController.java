package io.haitaoc.controller;

import io.haitaoc.model.JSONResult;
import io.haitaoc.model.SysDeviceItems;
import io.haitaoc.service.SysDeviceItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysDeviceItemController {

    @Autowired
    private SysDeviceItemsService sysDeviceItemsService;

    @GetMapping("/getDeviceItemsByIp/{device_ip}/{page}/{pageSize}")
    public JSONResult sysDetailInfoByIp(@PathVariable String device_ip,@PathVariable Integer page,@PathVariable Integer pageSize){
        List<SysDeviceItems> sysDeviceItems = sysDeviceItemsService.getDeviceItemsByIp(device_ip,page,pageSize);
        return JSONResult.ok(sysDeviceItems);
    }

    @GetMapping("/getDeviceItemsByIp/{device_ip}/total")
    public JSONResult getRecordsNumber(@PathVariable String device_ip){
        int total = sysDeviceItemsService.recordsNumber(device_ip);
        return JSONResult.ok(total);
    }

}
