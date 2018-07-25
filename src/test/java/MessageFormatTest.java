import java.text.MessageFormat;

public class MessageFormatTest {

    public static void main(String[] args) {
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].deviceIp}, #'{'list[{0}].warnInfo}, #'{'list[{0}]findTime}, #'{'list[{0}].warnType})");
        String value = mf.format(new Object[]{1});
        System.out.println(value);
    }
}
