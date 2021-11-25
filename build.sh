#/bin/bash
VERSION="1.0"
javac -d target src/main/java/com/scheduler/*.java
jar cfe scheduler-v$VERSION.jar com.scheduler.Main -C target/ .
rm -rf target