package io.haitaoc.util;


import java.util.regex.Pattern;

/**
 * 产生一条随机的告警信息记录(告警信息与告警类型对应)
 */
public class MockData {

    public static String randomBusinessStatus() {
        int[] sysId = new int[]{1, 2, 3, 4, 5, 6};

        String[] warnInfo = new String[]{
                "系统异常", "委托异常", "交易异常"
        };

        String[] warnType = new String[]{
                "sys_status", "deleg_status", "trade_status",
        };

        String[] warnLevel = new String[]{
                "严重", "警告"
        };

        // 0.0<= Math.random() <1.0, 需要warnInfo与warnType对应, 所以单独求他们共同的index.
        int warnIndex = (int) (Math.random() * warnInfo.length);

        return sysId[(int) (Math.random() * sysId.length)] + "\t" + warnInfo[warnIndex] + "\t" +
                TimeUtil.nowLocalDateTimeWith3MilliToString() + "\t" + warnType[warnIndex] +
                "\t" + warnLevel[(int) (Math.random() * warnLevel.length)];

    }

    public static String randomDeviceItem() {
        String[] ip = new String[]{
                "127.0.0.1", "127.0.0.3", "127.0.0.6", "127.0.0.9", "127.0.0.2",
                "127.0.0.4", "127.0.0.5", "127.0.0.7", "127.0.0.8"
        };

        String[] warnInfo = new String[]{
                "CPU异常", "数据库异常", "网络异常", "内存异常"
        };

        String[] warnType = new String[]{
                "cpu_status", "db_status", "network_status", "memory_status"
        };

        String[] warnLevel = new String[]{
                "严重", "警告"
        };

        // 0.0<= Math.random() <1.0, 需要warnInfo与warnType对应, 所以单独求他们共同的index.
        int warnIndex = (int) (Math.random() * warnInfo.length);

        return ip[(int) (Math.random() * ip.length)] + "\t" + warnInfo[warnIndex] + "\t" +
                TimeUtil.nowLocalDateTimeWith3MilliToString() + "\t" + warnType[warnIndex] +
                "\t" + warnLevel[(int) (Math.random() * warnLevel.length)];
    }

    /*
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) {

        System.out.println(MockData.randomDeviceItem());
        System.out.println(MockData.randomBusinessStatus());
        System.out.println(MockData.isInteger("1"));
    }

}
