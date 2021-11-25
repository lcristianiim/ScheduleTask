package com.scheduler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void givenStarterNotRunning_ShouldStartStarter() {
        FileServiceTestImpl service = new FileServiceTestImpl();
        service.setStarterRunning(false);

        Operations operations = new Operations(service);
        operations.startStarterIfIsNotAlreadyStarted();

        assertTrue(service.isStartingStarterTriggered());
    }

    @Test
    void givenStarterRunning_ShouldNotStartStarter() {
        FileServiceTestImpl service = new FileServiceTestImpl();
        service.setStarterRunning(true);

        Operations operations = new Operations(service);
        operations.startStarterIfIsNotAlreadyStarted();

        assertFalse(service.isStartingStarterTriggered());
    }

    @Test
    void givenLogFileAlreadyExisting_ShouldNotCreateLogFile() {
        FileServiceTestImpl service = new FileServiceTestImpl();
        service.setStarterLogFileExisting(true);

        Operations operations = new Operations(service);
        operations.createStarterLog(10);

        assertFalse(service.isCreateLogFileTriggered());
    }

    @Test
    void givenNotExistingLogFile_ShouldCreateLogFile() {
        FileServiceTestImpl service = new FileServiceTestImpl();
        service.setStarterLogFileExisting(false);

        Operations operations = new Operations(service);
        operations.createStarterLog(10);

        assertTrue(service.isCreateLogFileTriggered());
    }

}