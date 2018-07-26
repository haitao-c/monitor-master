package io.haitaoc.dao;


import io.haitaoc.dao.provider.SysDeviceItemsDaoProvider;
import io.haitaoc.dao.provider.WarnDaoProvider;
import io.haitaoc.model.SysDeviceItems;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

public interface SysDeviceItemsDao {

    @Select("select DISTINCT device_ip from sys_device_items where sys_id = #{sysId}")
    List<SysDeviceItems> findDeviceItemsBySysId(int sysId);

    /**
     * 找出不同时间点下所有设备Ip对应的监控项
     */
    @Select("select * from sys_device_items where device_ip = #{device_ip}")
    List<SysDeviceItems> getDeviceItemsByIp(String device_ip);

    @Insert("insert into sys_device_items(device_ip,cpu_status,memory_status,network_status,db_status,business_status,datetime,sys_id) values(" +
            "#{deviceIp},#{cpuStatus},#{memoryStatus},#{networkStatus},#{dbStatus},#{businessStatus},#{dateTime},#{sysId})")
    void insert(SysDeviceItems record);

    /**
     * 根据要更新的warn对应的ip,warn_type,find_time找到sys_device_item表中的记录
     */
    @Select("select * from sys_device_items where device_ip = #{deviceIp} and ${warn_type}=0 and datetime=#{findTime}")
    SysDeviceItems findOne(@Param("deviceIp") String deviceIp,@Param("warn_type") String warn_type,@Param("findTime") LocalDateTime findTime);

    @Update("update sys_device_items set ${warn_type}=1,datetime=#{findTime} where id=#{id}")
    void updateOne(@Param("id") long id, @Param("warn_type") String warn_type, @Param("findTime") LocalDateTime findTime);

    // 根据log文件读取到的告警内容赋值给SysDeviceItems实例, 批量插入到对应的sys_device_items表中
    @InsertProvider(type = SysDeviceItemsDaoProvider.class, method = "insertAll")
    void insertBatch(@Param("list") List<SysDeviceItems> sysDeviceItems);

}
