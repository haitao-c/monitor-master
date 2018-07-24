package io.haitaoc.service;


import io.haitaoc.model.SysCategory;
import java.util.List;

public interface SysCategoryService {

    /**
     * 找出所有分类以及对应分类下的系统和监控项
     */
    List<SysCategory> allCategoryWithSysAndDevice();

    /**
     *  找出所有分类以及对应的系统
     */
    List<SysCategory>  allCategoryWithSys();
}
