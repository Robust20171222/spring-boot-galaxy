#!/bin/bash

#判断HiveServer2进程是否存在，如果不存在就启动它
PIDS=`ps -ef |grep HiveServer2 |grep -v grep | awk '{print $2}'`

if [ "$PIDS" != "" ]; then
    time=$(date "+%Y-%m-%d %H:%M:%S")
    echo "${time} HiveServer2 is runing!"
else
    #运行HiveServer2进程
    sleep 30
    source /etc/profile
    cd /usr/local/apache-hive-1.2.1-bin/logs
    nohup hive --service hiveserver2 &
fi