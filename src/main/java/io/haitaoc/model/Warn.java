package io.haitaoc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Warn {
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String deviceIP;

    @Getter
    @Setter
    private String warnInfo;

    @Getter
    @Setter
    private String confirm;

    @Getter
    @Setter
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime findTime;

    @Getter
    @Setter
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Getter
    @Setter
    private String warnType;

}