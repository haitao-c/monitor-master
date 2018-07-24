package io.haitaoc.dao;

import io.haitaoc.model.Warn;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

public interface WarnDao {

    // 找出所有出现警告信息的设备IP
    @Select("SELECT DISTINCT device_ip FROM warn")
    List<Warn> findDistinctIP();

    @Select("SELECT * FROM warn WHERE id=#{id}")
    Warn findById(long id);

    // 根据IP找出对应的所有警告信息个数
    @Select("SELECT COUNT(*) FROM warn WHERE confirm is null and device_ip=#{device_ip}")
    int warnCount(String device_ip);

    // 找出对应IP的所有警告(尚未confirm的警告)
    @Select("SELECT * FROM warn WHERE confirm is null or confirm='' and device_ip=#{device_ip}")
    List<Warn> findIpWarns(String device_ip);

    // 找出对应IP的所有警告(已经confirm的警告)
    @Select("SELECT * FROM warn WHERE confirm is not null and device_ip=#{device_ip}")
    List<Warn> findConfirmedIpWarns(String device_ip);

    // 根据ID更新warn表中被修复的内容(更新confirm字段为手动修复)以及修复成功时的时间
    @Update("UPDATE warn set confirm = #{fixType}, update_time=#{updateTime} where id = #{id}")
    void updateConfirm(@Param("id") long id, @Param("updateTime") LocalDateTime updateTime, @Param("fixType") String fixType);


}
