package io.haitaoc.task;


import io.haitaoc.model.SysDeviceItems;
import io.haitaoc.model.Warn;
import io.haitaoc.service.SysDeviceItemsService;
import io.haitaoc.service.WarnService;
import io.haitaoc.util.DeviceSysIdMapUtil;
import io.haitaoc.util.MockData;
import io.haitaoc.util.TimeUtil;
import io.haitaoc.util.WarnTypeStatusMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Task {

    @Autowired
    private WarnService warnService;

    @Autowired
    private SysDeviceItemsService sysDeviceItemsService;

    private final int BATCH_INSERT_COUNT = 2;

    List<Warn> warns = new ArrayList<>();
    List<SysDeviceItems> sysDeviceItems = new ArrayList<>();

    public Task() throws FileNotFoundException {

    }

    // 要操作的文件
    File f = new File("D:\\records.log");
    // 声明要追加写入, 即设置第二个参数为true
    FileOutputStream fos = new FileOutputStream(f, true);
    PrintWriter out = new PrintWriter(fos);

    // 声明对文件进行只读操作
    RandomAccessFile raf = new RandomAccessFile(f, "r");
    long seek = 0;

    // 定时写入文件一条随机的告警信息
    @Scheduled(fixedRate = 6000)
    public void write() throws IOException {

        out.println(MockData.randomOne());
        out.flush();
    }

    // 定时读取文件内容, 时间要比定时写入晚
    @Scheduled(fixedRate = 9000)
    public void read() throws IOException {

        raf.seek(seek);

        /**
         * 批量插入读取到的告警信息
         */
//        if (raf.getFilePointer() != raf.length()) {
        if (warns.size() != BATCH_INSERT_COUNT && raf.getFilePointer() != raf.length()) {
            // RandomAccessFile的readLine方法会将读取上来的文本转换为ISO-8859-1, 所以要用指定的UTF-8编码读取文件中的内容
            String record = new String(raf.readLine().getBytes("ISO-8859-1"), "utf-8");
            if (record.equals(""))
                return;
            String[] all = record.split("\t");
            String deviceIp = null;
            String warnInfo = null;
            LocalDateTime findTime = null;
            String warnType = null;

            deviceIp = all[0];
            warnInfo = all[1];
            findTime = TimeUtil.milli3StringToLocalDateTime(all[2]);
            warnType = all[3];

            Warn warn = new Warn();
            warn.setDeviceIP(deviceIp);
            warn.setWarnInfo(warnInfo);
            warn.setFindTime(findTime);
            warn.setWarnType(warnType);
            warns.add(warn);

            /**
             * 对sysDeviceItem进行赋值处理
             */
            SysDeviceItems sysDeviceItem = new SysDeviceItems();
            setProperties(sysDeviceItem, deviceIp, warnType, findTime);
            sysDeviceItems.add(sysDeviceItem);

            System.out.println(deviceIp + " " + warnInfo + " " + findTime + " " + warnType);
            seek = raf.getFilePointer();

        } else {
            if (!warns.isEmpty()) {
                warnService.insertBatch(warns);
                warns.clear();
            }
            if (!sysDeviceItems.isEmpty()) {
                sysDeviceItemsService.insertBarch(sysDeviceItems);
                sysDeviceItems.clear();
            }
        }
    }

    public void setProperties(SysDeviceItems sysDeviceItem, String deviceIp, String warnType, LocalDateTime dateTime) {
        // 设置deviceIp属性
        sysDeviceItem.setDeviceIp(deviceIp);
        // 设置告警信息中warn_type对应到sys_device_items表中的状态为false, 并设置所有没有警告信息的状态为true
        try {
            setStatus(sysDeviceItem, warnType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // 设置dateTime
        sysDeviceItem.setDateTime(dateTime);
        // 设置对应的sysId
        DeviceSysIdMapUtil mapUtil = new DeviceSysIdMapUtil();
        int sysId = mapUtil.get(deviceIp);
        sysDeviceItem.setSysId(sysId);

    }

    public void setStatus(SysDeviceItems sysDeviceItem, String warnType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取SysDeviceItems中的所有方法
        Method[] methods = sysDeviceItem.getClass().getDeclaredMethods();
        String statusMethod = null;
        WarnTypeStatusMapUtil mapUtil = new WarnTypeStatusMapUtil();
        // 找到对应的告警状态后将其设置为异常(false)
        for (Method method : methods) {
            String name = method.getName();
            if (name.equals(mapUtil.get(warnType))) {
                statusMethod = name;
                Method m = sysDeviceItem.getClass().getDeclaredMethod(statusMethod, boolean.class);
                m.invoke(sysDeviceItem, false);
            } else if (name.startsWith("set") && name.endsWith("Status")) {
                // 其他状态设置为true(正常)
                statusMethod = name;
                Method m = sysDeviceItem.getClass().getDeclaredMethod(statusMethod, boolean.class);
                m.invoke(sysDeviceItem, true);
            }
        }

    }
}
