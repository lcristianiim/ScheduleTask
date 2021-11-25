[![schedule-task](https://github.com/lcristianiim/ScheduleTask/raw/main/static/project-image.png)](#)
# ScheduleTask
You want a program to run for a specified amount of time and in that time to run you're specified terminal command at a fixed specified interval of time conditioned by a presence of a specified file? Then Stop searching ScheduleTask is here to help.

## Prerequisites
java 8

## Install
```
run `build.sh` # This will create the runnable jar file. (to modify the version edit the `build.sh` script)
```
 
## Run
run `java -jar scheduler-v<version>.jar` with desired parameters

## Parameters
`first` - number of seconds of how long the program should run. After this amount of seconds the program will exit. 

`second` - number of seconds used for the fix rate. Your custom command will be run at every number of seconds you specify here.

`third` - Log file pattern. This file will be created when ScheduleTask starts.

`forth` - file to check if it is present or not

`fifth` - custom terminal command

## Example
```
java -jar scheduler-v1.0.jar 13 2 "starter-<date>.txt" "/tmp/tmp/cool-file-<pid>" "touch created.md"
```
