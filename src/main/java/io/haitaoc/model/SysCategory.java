package io.haitaoc.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SysCategory {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private List<AppSys> appSys;

}
