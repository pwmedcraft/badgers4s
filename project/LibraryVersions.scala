import sbt._

object LibraryVersions {

  val http4sVersion = "0.20.1"
  val circeVersion = "0.10.0"
  val dropWizardMetricsVersion = "4.1.0"

  val typesafeConfig = "com.typesafe" % "config" % "1.3.4"

  val catsEffect = "org.typelevel" %% "cats-effect" % "1.3.0"

  val http4sCore = "org.http4s" %% "http4s-dsl" % http4sVersion
  val http4sServer = "org.http4s" %% "http4s-blaze-server" % http4sVersion
  val http4sCirce = "org.http4s" %% "http4s-circe" % http4sVersion
  val http4sDropwizardMetrics = "org.http4s" %% "http4s-dropwizard-metrics" % http4sVersion

  val circeCore = "io.circe" %% "circe-core" % circeVersion
  val circeGeneric = "io.circe" %% "circe-generic" % circeVersion
  val circeParser = "io.circe" %% "circe-parser" % circeVersion
  val circeGenericExtras = "io.circe" %% "circe-generic-extras" % circeVersion
  val circeJava8 = "io.circe" %% "circe-java8" % circeVersion

  val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
  val journal = "io.verizon.journal" %% "core" % "3.0.19"

  val metricsCore = "io.dropwizard.metrics" % "metrics-core" % dropWizardMetricsVersion
  val metricsHealthChecks = "io.dropwizard.metrics" % "metrics-healthchecks" % dropWizardMetricsVersion
  val metricsJson = "io.dropwizard.metrics" % "metrics-json" % dropWizardMetricsVersion
  val metricsJvm = "io.dropwizard.metrics" % "metrics-jvm" % dropWizardMetricsVersion

  //test
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  val scalaTestCirce = "com.stephenn" %% "scalatest-circe" % "0.0.1" % "test"
  val pegdown = "org.pegdown" % "pegdown" % "1.6.0" % "test"
}