import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RandomAccessFileTest {
    public static void main(String args[]) throws IOException, InterruptedException {
        read();
    }

    public static void write() throws IOException {
        //以读写的方式来访问该文件
        RandomAccessFile raf = new RandomAccessFile("D:\\test.txt", "rw");
        raf.writeBytes("Hello World!");
        raf.writeBoolean(true);
        raf.writeInt(30);
        raf.writeDouble(3.56);
        raf.close();
    }

    public static void read() throws IOException, InterruptedException {
        File f = new File("D:\\test.txt");
        RandomAccessFile raf = new RandomAccessFile(f, "r");
        // 输出
        // 第二个参数设置为tru,表示追加内容
        FileOutputStream fos = new FileOutputStream(f,true);
        PrintWriter out = new PrintWriter(fos);
        out.println("127.0.0.1\tCPU异常\t2018-07-19 15:50:08.000\tcpu_status");
        out.flush();
        // raf.seek(raf.length());//设置指针的位置
        raf.seek(0);
        out.println("127.0.0.1\t正常 \t2018-07-19 15:50:08.000");
        out.flush();
        // RandomAccessFile的readLine方法会将读取上来的文本转换为ISO-8859-1, 所以要用指定的UTF-8编码读取文件中的内容
        System.out.println(new String(raf.readLine().getBytes("ISO-8859-1"), "utf-8"));
        // Thread.sleep(3000);
        System.out.println("当前指针位置： "+raf.getFilePointer());
        raf.seek(raf.getFilePointer());
        out.println("127.0.0.1\t正常 \t2018-07-19 15:50:08.000");
        out.flush();
        System.out.println(new String(raf.readLine().getBytes("ISO-8859-1"), "utf-8"));
        raf.seek(raf.getFilePointer());
        System.out.println(raf.getFilePointer());
    }
}
