import LibraryVersions._

lazy val root = Project("skeleton", file("."))
  .settings(Seq(
    name := "skeleton",
    version := Option(System.getenv("BUILD_NUMBER")).getOrElse("DEV-SNAPSHOT"),
    organization := "pwmedcraft",
    scalaVersion := "2.12.8",
    scalaBinaryVersion := "2.12",
    fork in Test := true,
    parallelExecution in Test := true,
    libraryDependencies ++= Seq(
      typesafeConfig,
      http4sCore, http4sServer,
      logback, journal,
      scalaTest
    ),
    testOptions in Test ++= Seq(
      Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports")
    )
  ))