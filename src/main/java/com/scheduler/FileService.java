package com.scheduler;

import java.time.LocalDateTime;

public interface FileService {
    void createLogFile();
    void addLog(String message);
    boolean isStarterRunning();
    void startStarter();
    boolean isLogFileAlreadyCreated();
    String getFileToCheck();
    LocalDateTime getNow();
}
