#!/bin/sh
# NF 每一行 （$0） 拥有的字段总数
cpuUsage=`top -bn1 | grep load | awk '{printf "\"cpuUsage\":%.2f", $(NF-2)}'`
diskUsage=`df -h|awk '$NF=="/" { print $(NF-1) }'|awk -F'%' '{printf "\"diskUsage\":" $1}'`
memoryUsage=`free -m | awk 'NR==2{printf "\"memoryUsage\":%.2f", $3*100/$2 }'`
createTime=`date '+"createTime":"%Y-%m-%d %H:%M:%S'\"`
curl -X POST -H "Content-type:application/json" -d "{$cpuUsage,$diskUsage,$memoryUsage,$createTime}" 'http://127.0.0.1:8080/status/addMessage'