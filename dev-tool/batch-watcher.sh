#!/bin/bash

while [ true ]; do

# 최신소스 가져오기
git pull

# 빌드
cd /Users/wedul/Documents/project/wedul_timeline/wedul_timeline
gradle build -x test
processCnt=`ps -ef | grep 'java -jar' | wc -l`
echo $processCnt

if [ $processCnt -eq 2 ]; then
# process 죽이기
ps -ef | grep 'java -jar' | grep -v grep | awk '{print $2}' | xargs kill
fi

# 다시 실행
nohup java -jar /Users/wedul/Documents/project/wedul_timeline/wedul_timeline/batch/build/libs/batch-1.0.0.war &

sleep 3600
done
