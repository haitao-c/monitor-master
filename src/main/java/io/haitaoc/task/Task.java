package io.haitaoc.task;


import io.haitaoc.model.SysBusiness;
import io.haitaoc.model.SysDeviceItems;
import io.haitaoc.model.Warn;
import io.haitaoc.service.SysBusinessService;
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

    @Autowired
    private SysBusinessService sysBusinessService;

    //批量插入的记录条数
    private final int BATCH_INSERT_COUNT = 2;

    List<Warn> warns = new ArrayList<>();
    List<SysDeviceItems> sysDeviceItems = new ArrayList<>();
    List<SysBusiness> sysBusinessList = new ArrayList<>();

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
    boolean flag = false;           // true-表示插入的是业务异常信息, false-表示插入的是设备硬件异常信息


    // 定时写入文件一条随机的告警信息
    @Scheduled(fixedRate = 6000)
    public void write() throws IOException {
        // 插入设备硬件监控项告警异常
        out.println(MockData.randomDeviceItem());
        // 插入业务状况告警异常
        out.println(MockData.randomBusinessStatus());
        out.flush();
    }

    // 定时读取文件内容, 时间要比定时写入晚
    @Scheduled(fixedRate = 9000)
    public void read() throws IOException {

        raf.seek(seek);

        /**
         * 批量插入读取到的告警信息
         */
        if (warns.size() != BATCH_INSERT_COUNT && raf.getFilePointer() != raf.length()) {
            // RandomAccessFile的readLine方法会将读取上来的文本转换为ISO-8859-1, 所以要用指定的UTF-8编码读取文件中的内容
            String record = new String(raf.readLine().getBytes("ISO-8859-1"), "utf-8");
            if (record.equals(""))
                return;
            String[] all = record.split("\t");
            String deviceIp = null;
            int sysId = 0;
            String warnInfo = null;
            LocalDateTime findTime = null;
            String warnType = null;
            String warnLevel = null;

            Warn warn = new Warn();

            // 如果读进的首信息是数字, 说明模拟的是插入业务异常信息, 否则插入的是设备硬件异常信息
            if (MockData.isInteger(all[0]))
                flag = true;

            if (flag) {
                sysId = Integer.parseInt(all[0]);
                warn.setSysId(sysId);
            } else {
                deviceIp = all[0];
                warn.setDeviceIP(deviceIp);
            }
            warnInfo = all[1];
            findTime = TimeUtil.milli3StringToLocalDateTime(all[2]);
            warnType = all[3];
            warnLevel = all[4];

            warn.setWarnInfo(warnInfo);
            warn.setFindTime(findTime);
            warn.setWarnType(warnType);
            warn.setWarnLevel(warnLevel);
            warns.add(warn);

            if (flag) {
                SysBusiness sysBusiness = new SysBusiness();
                setProperties(sysBusiness, sysId, warnType, findTime);
                sysBusinessList.add(sysBusiness);
            } else {
                // 对sysDeviceItem进行赋值处理
                SysDeviceItems sysDeviceItem = new SysDeviceItems();
                setProperties(sysDeviceItem, deviceIp, warnType, findTime);
                sysDeviceItems.add(sysDeviceItem);
            }
            System.out.println("插入异常数据");
            seek = raf.getFilePointer();

        } else {
            if (!warns.isEmpty()) {
                warnService.insertBatch(warns);
                warns.clear();
            }
            if (!sysDeviceItems.isEmpty()) {
                sysDeviceItemsService.insertBatch(sysDeviceItems);
                sysDeviceItems.clear();
            }
            if (!sysBusinessList.isEmpty()) {
                sysBusinessService.insertBatch(sysBusinessList);
                sysBusinessList.clear();
            }
        }
        flag = false;
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

    public void setProperties(SysBusiness sysBusiness, int sysId, String warnType, LocalDateTime dateTime) {
        // 设置sysId属性
        sysBusiness.setSysId(sysId);
        // 设置告警信息中warn_type对应到sys_business表中的状态为false, 并设置所有没有警告信息的状态为true
        try {
            setStatus(sysBusiness, warnType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // 设置scanTime
        sysBusiness.setScanTime(dateTime);
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

    public void setStatus(SysBusiness sysBusiness, String warnType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取SysDeviceItems中的所有方法
        Method[] methods = sysBusiness.getClass().getDeclaredMethods();
        String statusMethod = null;
        WarnTypeStatusMapUtil mapUtil = new WarnTypeStatusMapUtil();
        // 找到对应的告警状态后将其设置为异常(false)
        for (Method method : methods) {
            String name = method.getName();
            if (name.equals(mapUtil.get(warnType))) {
                statusMethod = name;
                Method m = sysBusiness.getClass().getDeclaredMethod(statusMethod, boolean.class);
                m.invoke(sysBusiness, false);
            } else if (name.startsWith("set") && name.endsWith("Status")) {
                // 其他状态设置为true(正常)
                statusMethod = name;
                Method m = sysBusiness.getClass().getDeclaredMethod(statusMethod, boolean.class);
                m.invoke(sysBusiness, true);
            }
        }
    }

}
