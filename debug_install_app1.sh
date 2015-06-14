#!/bin/sh

./gradlew clean asDeb -p apps/a101-app-1 && find apps/a101-app-1/ -type f -regex ".*-debug-[0-9].*\.apk" -exec adb install -r {} \; && ./log.sh


