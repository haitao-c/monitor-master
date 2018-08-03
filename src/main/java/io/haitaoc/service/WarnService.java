package io.haitaoc.service;

import io.haitaoc.model.Warn;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface WarnService {

    Map<String, Integer> deviceWarnCounts();

    Map<Integer, Integer> businessWarnCounts();

    List<Warn> findIpWarns(String deviceIp);

    List<Warn> findIdWarns(int sysId);

    List<Warn> findConfirmedIpWarns(String deviceIp);

    List<Warn> findConfirmedIdWarns(int sysId);

    void updateConfirm(long id, String fixType);

    void updateBusinessConfirm(long id, String fixType);

    void insertBatch(List<Warn> warns);
}
