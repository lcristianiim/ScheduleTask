package com.scheduler;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.scheduler.Operations.addingDateToFile;

public class Example {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);

        int howLongTheProgramShouldBeAliveInSeconds = 13;
        int checkingFileIntervalInSeconds = 2;
        LocalDateTime now = LocalDateTime.now();
        String logFileN = "starter-<date>.txt";
        String fileToCheck = "/tmp/tmp/cool-file-<pid>";
        String startCommand = "touch file-created-" + now;


        String logFileName = addingDateToFile(logFileN, now);
        FileService service = new FileServiceHandler(now, logFileName, fileToCheck, startCommand);
        Operations operations = new Operations(service);
        operations.createStarterLog(howLongTheProgramShouldBeAliveInSeconds);

        final Runnable action = operations::startStarterIfIsNotAlreadyStarted;

        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(action, 0, checkingFileIntervalInSeconds, TimeUnit.SECONDS);

        scheduler.schedule(() -> {
            beeperHandle.cancel(true);
            scheduler.shutdown();
            service.addLog("Stater shutdown");

        }, howLongTheProgramShouldBeAliveInSeconds, TimeUnit.SECONDS);
    }
}
