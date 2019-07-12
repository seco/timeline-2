#!/bin/bash

while [ true ]; do

sleep 10
processCnt=`ps -ef | grep 'java -jar' | wc -l`
echo $processCnt

if [ $processCnt -lt 2 ]; then
echo "restart java module"
cd /var/services/homes/wedul/project
nohup java -jar -Dspring.profiles.active=production api-1.0.0.war &
fi

done
