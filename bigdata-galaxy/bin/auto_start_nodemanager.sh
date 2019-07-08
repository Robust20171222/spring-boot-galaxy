#!/bin/bash

#判断NodeManager进程是否存在，如果不存在就启动它
PIDS=`ps -ef |grep NodeManager |grep -v grep | awk '{print $2}'`

if [ "$PIDS" != "" ]; then
    time=$(date "+%Y-%m-%d %H:%M:%S")
    echo "${time} NodeManager is runing!"
else
    #运行NodeManager进程
    sleep 60
    source /etc/profile
    $HADOOP_HOME/sbin/yarn-daemon.sh start nodemanager
fi