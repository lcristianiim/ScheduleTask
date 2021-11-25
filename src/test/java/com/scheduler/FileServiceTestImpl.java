package com.scheduler;

import java.time.LocalDateTime;

public class FileServiceTestImpl implements FileService {

    boolean isStartingStarterTriggered;
    boolean isStarterRunning;
    boolean isStarterLogFileExisting;
    boolean isCreateLogFileTriggered;

    @Override
    public void createLogFile() {
        isCreateLogFileTriggered = true;
    }

    @Override
    public void addLog(String message) {

    }

    @Override
    public boolean isStarterRunning() {
        return isStarterRunning;
    }

    @Override
    public void startStarter() {
        isStartingStarterTriggered = true;
    }

    @Override
    public boolean isLogFileAlreadyCreated() {
        return isStarterLogFileExisting;
    }

    @Override
    public String getFileToCheck() {
        return null;
    }

    @Override
    public LocalDateTime getNow() {
        return LocalDateTime.of(1,1,1,1,1);
    }

    public void setStarterRunning(boolean starterRunning) {
        isStarterRunning = starterRunning;
    }

    public boolean isStartingStarterTriggered() {
        return isStartingStarterTriggered;
    }

    public boolean isStarterLogFileExisting() {
        return isStarterLogFileExisting;
    }

    public void setStarterLogFileExisting(boolean StarterLogFileExisting) {
        isStarterLogFileExisting = StarterLogFileExisting;
    }

    public boolean isCreateLogFileTriggered() {
        return isCreateLogFileTriggered;
    }
}
