package com.scheduler;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.scheduler.Operations.addingDateToFile;


public class Main {
    /*
    @first = howLongTheProgramShouldBeAliveInSeconds
    @second = checkingFileIntervalInSeconds
    @third = starter format
    @forth = file to check
    @fifth = command to run

     */
    public static void main(String[] args) {

        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);
        int howLongTheProgramShouldBeAliveInSeconds = Integer.parseInt(args[0]);
        int checkingFileIntervalInSeconds = Integer.parseInt(args[1]);
        LocalDateTime now = LocalDateTime.now();
        String logFileN = args[2];
        String fileToCheck = args[3];
        String startCommand = args[4];

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