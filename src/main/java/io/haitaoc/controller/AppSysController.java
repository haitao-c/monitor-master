package io.haitaoc.controller;

import io.haitaoc.model.AppSys;
import io.haitaoc.model.JSONResult;
import io.haitaoc.service.AppSysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//TODO: 统一处理异常

@RestController
public class AppSysController {

    @Autowired
    private AppSysService appSysService;

    final static Logger log = LoggerFactory.getLogger(AppSysController.class);

    @GetMapping("/getSysDeviceItemInfo")
    public JSONResult basicInfo(){
        List<AppSys> allSysDeviceItemInfo = appSysService.getAllSysAndDeviceItems();
        return JSONResult.ok(allSysDeviceItemInfo);
    }

    @GetMapping("/getSysWithDeviceItems/{sysId}")
    public JSONResult sysDetailInfo(@PathVariable int sysId){
        AppSys appSys = appSysService.getSysWithDeviceItems(sysId);
        appSys.setSysId(sysId);
        return JSONResult.ok(appSys);
    }



    /**
     * PageHelper的分页使用
     * @param page
     * @return
     */
    @RequestMapping("/queryUserListPaged/{page}")
    public JSONResult queryUserListPaged(@PathVariable(name = "page") Integer page) {

        if (page == null) {
            page = 1;
        }

        int pageSize = 1;

     /*   User user = new User();
//		user.setNickname("lee");

        List<User> userList = userService.queryUserListPaged(user, page, pageSize);*/

        log.info("===================================");
        return JSONResult.ok();
    }
}
