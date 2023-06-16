#!/bin/bash

DB_NAME="postgres"
DATE=$(date +"%Y%m%d%H%M%S")

BACKUP_CMD="docker exec 1a07fa0edb963b78c352d6fb6021f3c7337cc19fae4837a45551c0b73875e362 pg_dump -U postgres -d ${DB_NAME} > ${DB_NAME}_${DATE}.sql"

eval $BACKUP_CMD
