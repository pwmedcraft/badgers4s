#!/bin/bash

SBT_OPTS="-Xmx512m -XX:+UseConcMarkSweepGC -Dapplication.name=skeleton"

sbt -Dconfig=local run