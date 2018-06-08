#!/usr/bin/env bash
nohup bin/zookeeper-server-start.sh config/zookeeper.properties > /dev/null 2>&1 &
sleep 2
nohup bin/kafka-server-start.sh config/server.properties > /dev/null 2>&1 &
sleep 2