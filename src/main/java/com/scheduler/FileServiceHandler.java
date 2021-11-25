package com.scheduler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public class FileServiceHandler implements FileService {
    String logFileName;
    String fileToCheck;
    String startCommand;
    LocalDateTime now;

    public FileServiceHandler(LocalDateTime now, String logFileName, String fileToCheck, String startCommand) {
        this.logFileName = logFileName;
        this.fileToCheck = fileToCheck;
        this.startCommand = startCommand;
        this.now = now;
    }

    @Override
    public void createLogFile() {
        Path log = Paths.get(logFileName);
        try {
            Files.createFile(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addLog(String message) {
        Path path = Paths.get(logFileName);
        now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");
        byte[] strToBytes = (now.format(formatter) + " " + message + System.lineSeparator()).getBytes();

        try {
            Files.write(path, strToBytes, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isStarterRunning() {
        String parentFolder = getParentFolder();
        String fileName = getFileName();

        boolean result = false;

        try (Stream<Path> paths = Files.walk(Paths.get(parentFolder))) {
            if(paths.anyMatch(file -> file.getFileName().toString().contains(fileName)))
                result = true;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    protected String getParentFolder() {
        List<String> splittedList = Arrays.asList(fileToCheck.split("/")).stream().collect(toCollection(ArrayList::new));
        splittedList.remove(splittedList.size() - 1);
        return String.join("/", splittedList);
    }

    @Override
        public void startStarter() {
            Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(startCommand);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean isLogFileAlreadyCreated() {
        return Files.exists(Paths.get(logFileName));
    }

    @Override
    public String getFileToCheck() {
        return fileToCheck;
    }

    @Override
    public LocalDateTime getNow() {
        return now;
    }

    public String getFileName() {
        List<String> splittedList = Arrays.asList(fileToCheck.split("/")).stream().collect(toCollection(ArrayList::new));
        String fileName = splittedList.get(splittedList.size() - 1);
        return fileName.replaceAll("<pid>", "");
    }
}
