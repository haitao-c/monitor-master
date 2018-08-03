package io.haitaoc.dao.provider;

import io.haitaoc.model.SysDeviceItems;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class SysBusinessDaoProvider {
    public String insertAll(Map map) {
        List<SysDeviceItems> sysDeviceItems = (List<SysDeviceItems>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO sys_business ");
        sb.append("(sys_status, deleg_status, trade_status, sys_id, scan_time) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].sysStatus}, #'{'list[{0}].delegStatus}, #'{'list[{0}].tradeStatus}, #'{'list[{0}].sysId}, #'{'list[{0}].scanTime})");
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
