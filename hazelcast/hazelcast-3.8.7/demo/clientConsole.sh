#!/bin/sh

java -Djava.net.preferIPv4Stack=true -cp ../lib/hazelcast-all-3.8.7.jar com.hazelcast.client.console.ClientConsoleApp
