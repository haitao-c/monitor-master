package io.haitaoc.dao.provider;

import io.haitaoc.model.Warn;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class WarnDaoProvider{
    public String insertAll(Map map) {
        List<Warn> warns = (List<Warn>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO warn ");
        sb.append("(device_ip, warn_info, find_time, warn_type) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].deviceIP}, #'{'list[{0}].warnInfo}, #'{'list[{0}].findTime}, #'{'list[{0}].warnType})");
        for (int i = 0; i < warns.size(); i++) {
            // 根据MessageFormat中的规则, 用Object数组中的数字i来替换list[{0}]中的0
            sb.append(mf.format(new Object[]{i}));
            if (i < warns.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

}
