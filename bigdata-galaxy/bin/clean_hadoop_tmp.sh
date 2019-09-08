#!/usr/bin/env bash
#
# Copyright (C) 2016 Julian Qian
#
# @file      clean_hadoop_tmp.sh
# @author    Julian Qian <junist@gmail.com>
# @created   2016-02-23 17:45:55
#

hadoop=hadoop
expired_days=7
now=$(date +%s)

$hadoop fs -du /tmp/hive-* | awk '{print $3}' | while read line; do
    file=${line##*/}
    date=${file:5:10}
    if [[ -n $date ]]; then
        days=$(( ($now - $(date +%s -d $date))/60/60/24 ))
        if (( $days > $expired_days )); then
            dir=$line
            echo "remove expired log $dir ..."
            sudo -u hdfs $hadoop fs -rm -r -skipTrash $dir
        fi
    fi
done