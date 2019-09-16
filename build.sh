#!/bin/sh

ps aux | grep -i 'java -jar' | awk '{print $2}' | xargs kill -9
nohup java -jar -Dspring.profiles.active=production /var/lib/jenkins/workspace/timeline/batch/build/libs/batch-1.0.0.war &
nohup java -jar -Dspring.profiles.active=production /var/lib/jenkins/workspace/timeline/api/build/libs/api-1.0.0.war &