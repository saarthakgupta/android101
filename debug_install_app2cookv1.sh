#!/bin/sh

./gradlew clean asDeb -p apps/a101-app-2 && find apps/a101-app-2/ -type f -regex ".*cookv1-debug-[0-9].*\.apk" -exec adb install -r {} \; && ./log.sh


