package io.haitaoc.service;

import io.haitaoc.model.AppSys;

import java.util.List;

public interface AppSysService {

    /**
     * 获取所有app_sys中的记录
     */
    List<AppSys> findAllAppSys();


    /**
     * 根据id找出每个app_sys对应的所有设备和监控项
     */
    AppSys getSysWithDeviceItems(int id);

    /**
     * 查询所有的每个系统对应的所有设备和监控项
     */
    List<AppSys> getAllSysAndDeviceItems();


    /**
     * 根据CategoryId查询该种类下对应的AppSys
     */
    List<AppSys> findByCategoryId(int cat_id);

}
