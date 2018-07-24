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

    @GetMapping("/getDeviceItemsByIp/{device_ip}")
    public JSONResult sysDetailInfoByIp(@PathVariable String device_ip){
        List<SysDeviceItems> sysDeviceItems = sysDeviceItemsService.getDeviceItemsByIp(device_ip);
        return JSONResult.ok(sysDeviceItems);
    }

}
