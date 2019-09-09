#!/bin/bash

#判断kudu-tserver进程是否存在，如果不存在就启动它
PIDS=`ps -ef |grep kudu-tserver |grep -v grep | awk '{print $2}'`

if [ "$PIDS" != "" ]; then
    time=$(date "+%Y-%m-%d %H:%M:%S")
    echo "${time} kudu-tserver is runing!"
else
    #启动kudu-tserver进程
    sleep 30
    source /etc/profile
    sudo /etc/init.d/kudu-tserver restart
fi