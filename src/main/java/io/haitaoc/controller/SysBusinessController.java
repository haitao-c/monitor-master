package io.haitaoc.controller;

import io.haitaoc.model.JSONResult;
import io.haitaoc.model.SysBusiness;
import io.haitaoc.service.SysBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysBusinessController {

    @Autowired
    private SysBusinessService sysBusinessService;

    @GetMapping("/getBusinessStatusById/{sys_id}/{page}/{pageSize}")
    public JSONResult sysDetailInfoByIp(@PathVariable int sys_id, @PathVariable Integer page, @PathVariable Integer pageSize){
        List<SysBusiness> sysBusinesses = sysBusinessService.getBusinessById(sys_id,page,pageSize);
        return JSONResult.ok(sysBusinesses);
    }

    @GetMapping("/getBusinessStatusById/{sys_id}/total")
    public JSONResult getRecordsNumber(@PathVariable int sys_id){
        int total = sysBusinessService.recordsNumber(sys_id);
        return JSONResult.ok(total);
    }
}
