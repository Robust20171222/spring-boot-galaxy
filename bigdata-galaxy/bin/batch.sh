#!/usr/bin/env bash
for line in $(cat slaves); do
    hostname=${line}
    echo $hostname
done