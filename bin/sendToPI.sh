#!/bin/bash
set -e

SDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"

cd $SDIR/..
sbt universal:packageBin
scp target/universal/*.zip pi@raspberrypi.local:~/
