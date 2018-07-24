package io.haitaoc.dao;

import io.haitaoc.model.AppSys;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AppSysDao {

    @Select("select * from app_sys")
    List<AppSys> findAll();

    @Select("select * from app_sys where sys_id=#{sys_id}")
    AppSys findOneById(int id);

    @Select("select * from app_sys where category_id=#{cat_id}")
    List<AppSys> findByCategoryId(int cat_id);

    /**
     * 根据AppSys的ID找出对应的所有设备数据
     * @param sys_id
     *  column = "sys_id" 是将app_sys表中的sys_id作为参数传递给SysDeviceItemsDao的findDeviceItemsBySysId
     * @return
     */
    @Select("select * from app_sys where sys_id = #{sys_id}")
    @Results({
            @Result(property = "deviceItems", column = "sys_id",
                    many = @Many(select = "io.haitaoc.dao.SysDeviceItemsDao.findDeviceItemsBySysId"))
    })
    AppSys getSysWithDeviceItems(int sys_id);



}
