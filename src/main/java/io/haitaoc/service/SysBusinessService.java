package io.haitaoc.service;

import io.haitaoc.model.SysBusiness;

import java.util.List;

public interface SysBusinessService {

    SysBusiness findBySysId(int sysId);

    void insertBatch(List<SysBusiness> sysBusinessList);

    List<SysBusiness> getBusinessById(int sys_id,int page,int pageSize);

    int recordsNumber(int sys_id);
}
