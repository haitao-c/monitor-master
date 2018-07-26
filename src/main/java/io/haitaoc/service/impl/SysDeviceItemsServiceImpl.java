package io.haitaoc.service.impl;

import com.github.pagehelper.PageHelper;
import io.haitaoc.dao.SysDeviceItemsDao;
import io.haitaoc.model.SysDeviceItems;
import io.haitaoc.service.SysDeviceItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysDeviceItemsServiceImpl implements SysDeviceItemsService {
    @Autowired
    private SysDeviceItemsDao sysDeviceItemsDao;

    @Override
    public List<SysDeviceItems> findDeviceItemsBySysId(int sysId) {
        List<SysDeviceItems> sysDeviceItems = sysDeviceItemsDao.findDeviceItemsBySysId(sysId);
        return sysDeviceItems;
    }


    // TODO: 展示所有告警信息时, 旧告警未解决, 设备监控表中新告警中没有包含旧报警(实际中, 每个报警在解决之前只出现一次), 问一下是不是要处理成积攒报警
    @Override
    public List<SysDeviceItems> getDeviceItemsByIp(String device_ip,int page,int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<SysDeviceItems> res = sysDeviceItemsDao.getDeviceItemsByIp(device_ip);
        return res;
    }

    @Override
    @Transactional
    public void insertBatch(List<SysDeviceItems> sysDeviceItems) {
        sysDeviceItemsDao.insertBatch(sysDeviceItems);
    }

    @Override
    public int recordsNumber(String device_ip) {
        return sysDeviceItemsDao.recordsNumber(device_ip);
    }

}
