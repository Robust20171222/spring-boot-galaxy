#!/bin/bash

#判断kudu-master进程是否存在，如果不存在就启动它
PIDS=`ps -ef |grep kudu-master |grep -v grep | awk '{print $2}'`

if [ "$PIDS" != "" ]; then
    time=$(date "+%Y-%m-%d %H:%M:%S")
    echo "${time} kudu-master is runing!"
else
    #启动kudu-master进程
    sleep 30
    source /etc/profile
    sudo /etc/init.d/kudu-master restart
fi