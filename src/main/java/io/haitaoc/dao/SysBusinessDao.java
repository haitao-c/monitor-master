package io.haitaoc.dao;

import io.haitaoc.dao.provider.SysBusinessDaoProvider;
import io.haitaoc.dao.provider.SysDeviceItemsDaoProvider;
import io.haitaoc.model.SysBusiness;
import io.haitaoc.model.SysDeviceItems;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

public interface SysBusinessDao {

    @Select("select * from sys_business where sys_id = #{sysId}")
    SysBusiness findBySysId(int sysId);

    // 根据log文件读取到的告警内容赋值给SysDeviceItems实例, 批量插入到对应的sys_device_items表中
    @InsertProvider(type = SysBusinessDaoProvider.class, method = "insertAll")
    void insertBatch(@Param("list")List<SysBusiness> sysBusinessList);

    /*** 找出不同时间点下所有系统Id对应的监控项*/
    @Select("select * from sys_business where sys_id = #{sys_id} order by scan_time desc")
    List<SysBusiness> getBusinessById(int sys_id);

    /*** 获取对应系统Id下的总记录数目*/
    @Select("select COUNT(*) from sys_business where sys_id = #{sys_id}")
    int recordsNumber(int sys_id);

    /*** 根据要更新的warn对应的系统id,warn_type,find_time找到sys_business表中的记录*/
    @Select("select * from sys_business where sys_id = #{sysId} and ${warn_type}=0 and scan_time=#{findTime}")
    SysBusiness findOne(@Param("sysId") int sysId,@Param("warn_type") String warn_type,@Param("findTime") LocalDateTime findTime);

    @Insert("insert into sys_business(sys_status, deleg_status, trade_status, scan_time, sys_id) values(" +
            "#{sysStatus},#{delegStatus},#{tradeStatus},#{scanTime},#{sysId})")
    void insert(SysBusiness record);

    @Update("update sys_business set ${warn_type}=1,scan_time=#{findTime} where id=#{id}")
    void updateOne(@Param("id") long id, @Param("warn_type") String warn_type, @Param("findTime") LocalDateTime findTime);


}
