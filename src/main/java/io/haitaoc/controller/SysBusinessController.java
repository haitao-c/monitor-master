package io.haitaoc.controller;

import io.haitaoc.model.JSONResult;
import io.haitaoc.model.SysBusiness;
import io.haitaoc.service.SysBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "API - SysBusinessController", description = "系统业务接口")
@RestController
public class SysBusinessController {

    @Autowired
    private SysBusinessService sysBusinessService;

    @ApiOperation(value = "获取业务状况记录详情", notes = "根据Id以及分页信息获取系统状况",response = JSONResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysId", value = "系统Id", required = true,
                    dataType = "int", paramType = "path", defaultValue = "1"),
            @ApiImplicitParam(name = "page", value = "当前页数", required = true,
                    dataType = "int", paramType = "path", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "展示条数", required = true,
                    dataType = "int", paramType = "path", defaultValue = "5"),
    })
    @GetMapping("/getBusinessStatusById/{sys_id}/{page}/{pageSize}")
    public JSONResult sysDetailInfoByIp(@PathVariable int sys_id, @PathVariable Integer page, @PathVariable Integer pageSize){
        List<SysBusiness> sysBusinesses = sysBusinessService.getBusinessById(sys_id,page,pageSize);
        return JSONResult.ok(sysBusinesses);
    }

    @ApiOperation(value = "获取业务详情总条数", notes = "根据系统Id获取记录总数",response = JSONResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysId", value = "系统Id", required = true,
                    dataType = "int", paramType = "path", defaultValue = "1"),
    })
    @GetMapping("/getBusinessStatusById/{sys_id}/total")
    public JSONResult getRecordsNumber(@PathVariable int sys_id){
        int total = sysBusinessService.recordsNumber(sys_id);
        return JSONResult.ok(total);
    }
}
