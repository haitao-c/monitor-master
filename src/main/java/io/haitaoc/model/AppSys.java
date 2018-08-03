package io.haitaoc.model;

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
    private int categoryId;

    @Getter
    @Setter
    private List<SysDeviceItems> deviceItems;

    @Getter
    @Setter                 // 一个系统对应一个系统的业务状态
    private SysBusiness sysBusiness;

}
