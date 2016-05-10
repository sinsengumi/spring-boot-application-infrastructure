#!/bin/sh

# 環境の設定
env=${1:-"development"}
port=${2:-"8080"}

# 起動
mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=${env} -Dserver.port=${port}"
