@ECHO OFF

set argC=0
for %%x in (%*) do Set /A argC+=1
set help=false

if %argC% equ 1 set help=true
if %argC% gtr 4 set help=true
if %argC% equ 1 ( echo "" if %1% equ "--help" help=true )

if %help% == true (
    echo usage: startManCenter.bat
    echo usage: startManCenter.bat [port] [path]
    echo usage: startManCenter.bat [port] [path] [classpath]
    echo usage: startManCenter.bat [port] [path] [classpath] [expandWarPath]
)

if %argC% == 4 (
    java -Djava.io.tmpdir="%4" -cp "mancenter-3.9.1.war;%3" Launcher %1 %2
)

if %argC% == 3 (
    if not exist %0\..\work mkdir %0\..\work
    java -cp "mancenter-3.9.1.war;%3" Launcher %1 %2
)

if %argC% == 2 (
    if not exist %0\..\work mkdir %0\..\work
    java -cp mancenter-3.9.1.war Launcher %1 %2
)

if %argC% == 0 (
    if not exist %0\..\work mkdir %0\..\work
    java -cp mancenter-3.9.1.war Launcher
)
