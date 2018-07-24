package io.haitaoc.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



public class SysDeviceItems {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String deviceIp;

    @Getter
    @Setter
    private boolean cpuStatus;

    @Getter
    @Setter
    private boolean memoryStatus;

    @Getter
    @Setter
    private boolean networkStatus;

    @Getter
    @Setter
    private boolean dbStatus;

    @Getter
    @Setter
    private boolean businessStatus;

    @Getter
    @Setter
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    @Getter
    @Setter
    private int sysId;


}