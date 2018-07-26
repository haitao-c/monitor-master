import io.haitaoc.util.TimeUtil;

import java.time.LocalDateTime;

public class TimeTest {

    public static void main(String[] args) {

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println(TimeUtil.nowLocalDateTimeWith3MilliToString());
        System.out.println(TimeUtil.milli3StringToLocalDateTime(TimeUtil.nowLocalDateTimeWith3MilliToString()));

    }
}
