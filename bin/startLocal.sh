#!/bin/bash

SBT_OPTS="-Xmx512m -XX:+UseConcMarkSweepGC -Dapplication.name=badgers4s"

sbt -Dconfig=local run