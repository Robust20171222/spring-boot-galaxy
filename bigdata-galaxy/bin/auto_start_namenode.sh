#!/bin/bash

#判断NameNode进程是否存在，如果不存在就启动它
PIDS=`ps -ef |grep NameNode |grep -v grep | awk '{print $2}'`

if [ "$PIDS" != "" ]; then
    time=$(date "+%Y-%m-%d %H:%M:%S")
    echo "${time} NameNode is runing!"
else
    #运行NameNode进程
    source /etc/profile
    $HADOOP_HOME/sbin/hadoop-daemon.sh start namenode
fi