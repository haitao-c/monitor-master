package io.haitaoc.service.impl;

import io.haitaoc.dao.AppSysDao;
import io.haitaoc.dao.SysDeviceItemsDao;
import io.haitaoc.model.AppSys;
import io.haitaoc.service.AppSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppSysServiceImpl implements AppSysService {
    @Autowired
    private AppSysDao appSysDao;

    @Autowired
    private SysDeviceItemsDao sysDeviceItemsDao;

    @Override
    public List<AppSys> findAllAppSys() {

        List<AppSys> allAppSys= appSysDao.findAll();

        return allAppSys;
    }

    @Override
    public AppSys getSysWithDeviceItems(int id) {
        AppSys appSys = appSysDao.getSysWithDeviceItems(id);
        return appSys;
    }

    @Override
    public List<AppSys> getAllSysAndDeviceItems() {
        List<AppSys> allSysAndDeviceItems = new ArrayList<>();
        List<AppSys> allAppSys= findAllAppSys();
        for(AppSys appSys:allAppSys){
            appSys.setDeviceItems(sysDeviceItemsDao.findDeviceItemsBySysId(appSys.getSysId()));
            allSysAndDeviceItems.add(appSys);
        }
        return allSysAndDeviceItems;
    }

    @Override
    public List<AppSys> findByCategoryId(int cat_id) {
        return appSysDao.findByCategoryId(cat_id);
    }

}
