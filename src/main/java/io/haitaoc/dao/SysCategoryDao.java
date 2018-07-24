package io.haitaoc.dao;

import io.haitaoc.model.SysCategory;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysCategoryDao {

    // 列出分类下的id和name
    @Select("select * from sys_category")
    List<SysCategory> findAll();



}
