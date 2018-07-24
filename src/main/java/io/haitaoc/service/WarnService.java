package io.haitaoc.service;

import io.haitaoc.model.Warn;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface WarnService {

    Map<String,Integer> deviceWarnCounts();

    List<Warn> findIpWarns(String deviceIp);

    List<Warn> findConfirmedIpWarns(String deviceIp);

    void updateConfirm(long id, String fixType);

}
