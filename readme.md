Basic Scala / http4s / Circe (/ Doobie - TODO!) backend demo app

You might have to do `sbt compile` from shell before running to build BuildInfo, or have IntelliJ set to use sbt shell for builds.

run with

`-Dconfig=local` or `-Dconfig=dev`

or use ```./bin/startLocal.sh```

Endpoints:

`/`
`/api/badgers`
`/api/badgers/{badger name}`  
`/status`  
`/status/json`  
`/status/version`  
`/metrics`