package io.haitaoc.service.impl;

import io.haitaoc.dao.SysDeviceItemsDao;
import io.haitaoc.model.SysDeviceItems;
import io.haitaoc.service.SysDeviceItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public List<SysDeviceItems> getDeviceItemsByIp(String device_ip) {
        List<SysDeviceItems> res = sysDeviceItemsDao.getDeviceItemsByIp(device_ip);
        return res;
    }

}
