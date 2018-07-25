package io.haitaoc.dao.provider;

import io.haitaoc.model.SysDeviceItems;
import io.haitaoc.model.Warn;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class SysDeviceItemsDaoProvider {
    public String insertAll(Map map) {
        List<SysDeviceItems> sysDeviceItems = (List<SysDeviceItems>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO sys_device_items ");
        sb.append("(device_ip, cpu_status, memory_status, network_status, db_status, business_status, datetime, sys_id) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].deviceIp}, #'{'list[{0}].cpuStatus}, #'{'list[{0}].memoryStatus}, #'{'list[{0}].networkStatus}, #'{'list[{0}].dbStatus}, #'{'list[{0}].businessStatus}, #'{'list[{0}].dateTime}, #'{'list[{0}].sysId})");
        for (int i = 0; i < sysDeviceItems.size(); i++) {
            // 根据MessageFormat中的规则, 用Object数组中的数字i来替换list[{0}]中的0
            sb.append(mf.format(new Object[]{i}));
            if (i < sysDeviceItems.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
