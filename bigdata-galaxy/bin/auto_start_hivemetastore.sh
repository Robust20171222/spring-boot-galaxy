#!/bin/bash

#判断HiveMetaStore进程是否存在，如果不存在就启动它
PIDS=`ps -ef |grep HiveMetaStore |grep -v grep | awk '{print $2}'`

if [ "$PIDS" != "" ]; then
    time=$(date "+%Y-%m-%d %H:%M:%S")
    echo "${time} HiveMetaStore is runing!"
else
    #运行HiveMetaStore进程
    sleep 30
    source /etc/profile
    cd /usr/local/apache-hive-1.2.1-bin/logs
    nohup hive --service metastore &
fi