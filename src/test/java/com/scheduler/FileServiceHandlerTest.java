package com.scheduler;

import com.scheduler.FileServiceHandler;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceHandlerTest {
    @Test
    void shouldGetParentFolderOutOfFileNamePattern() {
        LocalDateTime now = LocalDateTime.now();
        String logFileN = "starter-<date>.txt";
        String fileToCheck = "/home/ScheduleTask/src/main/java/Starter-is-running-<pid>";
        String startCommand = "touch file-created-" + now;
        String logFileName = addingDateToFile(logFileN, now);

        FileServiceHandler service = new FileServiceHandler(now, logFileName, fileToCheck, startCommand);
        String result = service.getParentFolder();
        assertEquals("/home/ScheduleTask/src/main/java", result);

    }

    @Test
    void shouldGetFileNameOutOfFileNamePattern() {
        LocalDateTime now = LocalDateTime.now();
        String logFileN = "starter-<date>.txt";
        String fileToCheck = "/home/ScheduleTask/src/main/java/Starter-is-running-<pid>";
        String startCommand = "touch file-created-" + now;
        String logFileName = addingDateToFile(logFileN, now);

        FileServiceHandler service = new FileServiceHandler(now, logFileName, fileToCheck, startCommand);
        String result = service.getFileName();
        assertEquals("Starter-is-running-", result);
    }

    private static String addingDateToFile(String logFileN, LocalDateTime now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");
        return logFileN.replace("<date>", now.format(formatter));
    }

}