Base http4s / Circe / Doobie app to test library upgrades.

You might have to do `sbt compile` from shell before running to build BuildInfo, or have IntelliJ set to use sbt shell for builds.

run with

`-Dconfig=local` or `-Dconfig=dev`

or use ```./bin/startLocal.sh```

Endpoints:

`/`
`/api/badgers`
`/api/badgers/{badger name}`  
`/status`  
`/status/version`  
`/metrics`