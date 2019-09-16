#!/bin/bash
# bash Call
# sudo bash {PATH}/deploy.sh 8080 spring-boot-jenkins

# Field

# Server Port
# Ex) 8080
BATCH_PORT=8082
API_PORT=8081
# Service Name
# Ex) spring-boot-jenkins

# Function
function stop(){
    sudo echo "Stoping process"
    sudo fuser -n tcp -k $BATCH_PORT
    sudo fuser -n tcp -k $API_PORT
}

function start(){
    sudo echo "start process"
    BUILD_ID=dontKillMe sudo nohup java -jar -Dspring.profiles.active=production /var/lib/jenkins/workspace/timeline/batch/build/libs/batch-1.0.0.war &
    BUILD_ID=dontKillMe sudo nohup java -jar -Dspring.profiles.active=production /var/lib/jenkins/workspace/timeline/api/build/libs/api-1.0.0.war &
}

# Function Call
stop

start