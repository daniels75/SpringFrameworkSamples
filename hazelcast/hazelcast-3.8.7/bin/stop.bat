@echo off

SETLOCAL

set "CLASSPATH=%~dp0..\lib\hazelcast-all-3.8.7.jar"

taskkill /F /FI "WINDOWTITLE eq hazelcast %CLASSPATH%"