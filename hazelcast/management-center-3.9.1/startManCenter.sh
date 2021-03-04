#!/bin/sh

if [ "$1" = "--help" ] || [ $# -eq 1 ] || [ $# -gt 4 ] ; then
    echo "usage: startManCenter.sh"
    echo "usage: startManCenter.sh [port] [path]"
    echo "usage: startManCenter.sh [port] [path] [classpath]"
    echo "usage: startManCenter.sh [port] [path] [classpath] [expandWarPath]"
    exit;
fi

if [ $# -eq 4 ] ; then
    java -Djava.io.tmpdir="$4" -cp "mancenter-3.9.1.war:$3" Launcher "$1" "$2"
elif [ $# -eq 3 ] ; then
    mkdir -p "`dirname $0`/work"
    java -cp "mancenter-3.9.1.war:$3" Launcher "$1" "$2"
elif [ $# -eq 2 ] ; then
    mkdir -p "`dirname $0`/work"
    java -cp mancenter-3.9.1.war Launcher "$1" "$2"
else
    mkdir -p "`dirname $0`/work"
    java -cp mancenter-3.9.1.war Launcher
fi