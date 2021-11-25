package com.scheduler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Operations {
    FileService service;

    public Operations(FileService service) {
        this.service = service;
    }

    public void startStarterIfIsNotAlreadyStarted() {
        if(!service.isStarterRunning()) {
            service.addLog("Triggering command -> File " + service.getFileToCheck() + " was not found.");
            service.startStarter();
        } else {
            service.addLog("No action taken -> File " + service.getFileToCheck() + " was found.");
        }
    }


    public static String addingDateToFile(String logFileN, LocalDateTime now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");
        return logFileN.replace("<date>", now.format(formatter));
    }

    protected void createStarterLog(int howLongToHaveItStarted) {
        if(!service.isLogFileAlreadyCreated()) {
            service.createLogFile();
            Duration duration = Duration.ofSeconds(howLongToHaveItStarted);
            long howManyHoursToRun = duration.toHours();
            long howManyMinutesToRun = duration.toMinutes();
            long howManySecondsToRun = duration.getSeconds();

            LocalDateTime startedNow = service.getNow();
            LocalDateTime end = startedNow.plus(duration);
            long endHours = end.getHour();
            long endMinutes = end.getMinute();
            long endSeconds = end.getSecond();

            String startMessage =
                    String.format("Starter started. Will run for: %dh %dm %ds. It will stop at: %d:%d:%d",
                            howManyHoursToRun, howManyMinutesToRun, howManySecondsToRun, endHours, endMinutes, endSeconds);
            service.addLog(startMessage);
        }
    }
}
