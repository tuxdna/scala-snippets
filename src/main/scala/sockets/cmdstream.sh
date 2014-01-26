#!/usr/bin/bash

## Generate command stream
## consists of following commands:
## time
## hello<integer>: such as hello1, hello9 etc.


while true
do
    MSG="time"
    R=$(( $RANDOM % 10 ))
    if [ $R -gt 5 ]
    then
	MSG=hello$R
    fi
    # echo $(( $R * 100000 ))
    usleep $(( $R * 100000 ))
    echo $MSG
done

