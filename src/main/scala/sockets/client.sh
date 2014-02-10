#!/usr/bin/bash

for i in {1..10}
do
    ( bash cmdstream.sh | nc -t localhost 1090 ) &
done
