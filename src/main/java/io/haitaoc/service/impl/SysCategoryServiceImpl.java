package io.haitaoc.service.impl;

import io.haitaoc.dao.SysCategoryDao;
import io.haitaoc.dao.SysDeviceItemsDao;
import io.haitaoc.model.AppSys;
import io.haitaoc.model.SysCategory;
import io.haitaoc.service.AppSysService;
import io.haitaoc.service.SysBusinessService;
import io.haitaoc.service.SysCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysCategoryServiceImpl implements SysCategoryService {

    @Autowired
    private SysCategoryDao sysCategoryDao;

    @Autowired
    private AppSysService  appSysService;

    @Autowired
    private SysDeviceItemsDao sysDeviceItemsDao;

    @Autowired
    private SysBusinessService sysBusinessService;

    @Override
    public List<SysCategory> allCategoryWithSysAndDevice() {
        // 找出所有种类, 这时每个SysCategory的List<AppSys> = null;
        List<SysCategory> allSysCategory = sysCategoryDao.findAll();
        for(SysCategory sysCategory:allSysCategory){
            // 根据cat_id找出与cat_id对应的appSys
            List<AppSys> appSysList  = appSysService.findByCategoryId(sysCategory.getId());
            for (AppSys appSys: appSysList){
                // 为系统设置对应所有设备(包含硬件监控项)
                System.out.println(appSys.getSysId());
                appSys.setDeviceItems(sysDeviceItemsDao.findDeviceItemsBySysId(appSys.getSysId()));
                // 为系统设置业务监控项
                //appSys.setSysBusiness(sysBusinessService.findBySysId(appSys.getSysId()));
            }
            sysCategory.setAppSys(appSysList);
        }
        return allSysCategory;
    }

    @Override
    public List<SysCategory> allCategoryWithSys() {
        // 找出所有种类, 这时每个SysCategory的List<AppSys> = null;
        List<SysCategory> allSysCategory = sysCategoryDao.findAll();
        for(SysCategory sysCategory:allSysCategory) {
            // 根据cat_id找出与cat_id对应的appSys
            List<AppSys> appSysList = appSysService.findByCategoryId(sysCategory.getId());
            sysCategory.setAppSys(appSysList);
        }
        return allSysCategory;
    }
}
