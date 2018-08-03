package io.haitaoc.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class SysBusiness {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private boolean sysStatus;

    @Getter
    @Setter
    private boolean delegStatus;

    @Getter
    @Setter
    private boolean tradeStatus;

    @Getter
    @Setter
    private int sysId;

    @Getter
    @Setter
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime scanTime;

}
