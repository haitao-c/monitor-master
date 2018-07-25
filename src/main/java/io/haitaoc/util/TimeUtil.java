package io.haitaoc.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    /**
     * 获取当前系统时间对应的字符串, 保留三位毫秒数
     * @return
     */
    public static String nowLocalDateTimeWith3MilliToString(){
        // LocalDateTime.now()取了nanosec精确到毫秒后的三位 yyyy-MM:dd HH:mm:ss.zzz
        LocalDateTime time = LocalDateTime.now();
        long nanoSec = time.getNano();
        // 取nnn时,共9位的nano数据的最后三位显示(最后三位前均为0)
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nnn");
        time = time.withNano((int) (nanoSec/(Math.pow(10,6))));
        String localTime = df.format(time);
        return localTime;
    }

    /**
     * 将传递进来的表示保留三位毫秒的时间字符串转换为保留三位毫秒的LocalDateTime
     * @param
     */
    public  static LocalDateTime milli3StringToLocalDateTime(String localDateTime){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nnn");
        LocalDateTime ldt = LocalDateTime.parse(localDateTime,df);
        long nanoSec = ldt.getNano();
        ldt = ldt.withNano((int) (nanoSec*Math.pow(10,6)));
        return ldt;
    }


    public static void main(String[] args) {
        //System.out.println(TimeUtil.nowLocalDateTimeWith3MilliToString());
        String[] mockData = new String[]{
                "127.0.0.1\tCPU异常\t" + TimeUtil.nowLocalDateTimeWith3MilliToString() + "\tcpu_status",
        };
        System.out.println(mockData[0]);
        String[] data = mockData[0].split("\t");
        System.out.println(TimeUtil.milli3StringToLocalDateTime(data[2]));
    }

}
