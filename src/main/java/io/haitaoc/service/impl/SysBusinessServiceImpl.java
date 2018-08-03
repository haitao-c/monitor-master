package io.haitaoc.service.impl;

import com.github.pagehelper.PageHelper;
import io.haitaoc.dao.SysBusinessDao;
import io.haitaoc.model.SysBusiness;
import io.haitaoc.service.SysBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysBusinessServiceImpl implements SysBusinessService {

    @Autowired
    private SysBusinessDao sysBusinessDao;

    @Override
    public SysBusiness findBySysId(int sysId) {
        SysBusiness res = sysBusinessDao.findBySysId(sysId);
        return res;
    }

    @Override
    public void insertBatch(List<SysBusiness> sysBusinessList) {
        sysBusinessDao.insertBatch(sysBusinessList);
    }

    @Override
    public List<SysBusiness> getBusinessById(int sys_id, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<SysBusiness> res = sysBusinessDao.getBusinessById(sys_id);
        return res;
    }

    @Override
    public int recordsNumber(int sys_id) {
        int num = sysBusinessDao.recordsNumber(sys_id);
        return num;
    }


}
