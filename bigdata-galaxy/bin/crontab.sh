#!/usr/bin/env bash
*/3 * * * * /usr/local/hadoop-2.6.3/cron/auto_start_nodemanager.sh >/dev/null 2>&1
*/3 * * * * /usr/local/hadoop-2.6.3/cron/auto_start_datanode.sh >/dev/null 2>&1