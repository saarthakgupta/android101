#!/bin/sh

export GRADLE_OPTS="-Dorg.gradle.daemon=true"
./gradlew clean aR -p apps/a101-app-1 && find apps/a101-app-1/ -type f -regex ".*-release-[0-9].*\.apk" -exec adb install -r {} \; && ./log.sh


