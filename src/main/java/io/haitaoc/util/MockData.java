package io.haitaoc.util;


/**
 * 产生一条随机的告警信息记录(告警信息与告警类型对应)
 */
public class MockData {

    public  static String randomOne(){
         String[] ip = new String[]{
                "127.0.0.1", "127.0.0.3", "127.0.0.6", "127.0.0.9"
        };

         String[] warnInfo = new String[] {
                "CPU异常","数据库异常","网络异常","内存异常","业务异常"
        };

         String[] warnType = new String[]{
                "cpu_status","db_status","network_status","memory_status","business_status"
        };

        // 0.0<= Math.random() <1.0, 需要warnInfo与warnType对应, 所以单独求他们共同的index.
        int warnIndex =(int)(Math.random()*warnInfo.length);

        return ip[(int)(Math.random()*ip.length)]+"\t"+warnInfo[warnIndex]+"\t"+
                TimeUtil.nowLocalDateTimeWith3MilliToString()+"\t"+warnType[warnIndex];
    }

    public static void main(String[] args) {

        System.out.println(MockData.randomOne());
    }

}
