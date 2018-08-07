package io.haitaoc.util;

import java.util.HashMap;
import java.util.Map;

/** 用于映射设备IP以及到对应的系统分类 */
public class DeviceSysIdMapUtil {
    Map<String, Integer> map = new HashMap<>();

    public DeviceSysIdMapUtil(){
        map.put("127.0.0.1",1);
        map.put("127.0.0.2",1);
        map.put("127.0.0.3",2);
        map.put("127.0.0.4",2);
        map.put("127.0.0.5",3);
        map.put("127.0.0.6",3);
        map.put("127.0.0.7",4);
        map.put("127.0.0.8",5);
        map.put("127.0.0.9",6);
    }

    public int get(String ip){
        return map.get(ip);
    }

    public void add(String ip, int sysId){
        map.put(ip,sysId);
    }

    public static void main(String[] args) {
        DeviceSysIdMapUtil m = new DeviceSysIdMapUtil();
        System.out.println(m.get("127.0.0.1"));
    }
}
