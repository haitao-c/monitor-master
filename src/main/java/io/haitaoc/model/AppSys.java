package io.haitaoc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AppSys {

    @Getter
    @Setter
    private int sysId;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private List<SysDeviceItems> deviceItems;

    @Getter
    @Setter
    private int categoryId;
}
