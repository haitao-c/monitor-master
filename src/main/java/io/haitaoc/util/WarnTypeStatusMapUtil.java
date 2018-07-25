package io.haitaoc.util;

import java.util.HashMap;
import java.util.Map;

public class WarnTypeStatusMapUtil {

    Map<String,String> map = new HashMap<>();

    public WarnTypeStatusMapUtil(){
        map.put("cpu_status","setCpuStatus");
        map.put("memory_status","setMemoryStatus");
        map.put("network_status","setNetworkStatus");
        map.put("db_status","setDbStatus");
        map.put("business_status","setBusinessStatus");
    }

    public String get(String warn_type){
        return map.get(warn_type);
    }

    public void add(String warn_type,String method){
        map.put(warn_type,method);
    }

}
