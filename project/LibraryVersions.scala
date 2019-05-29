import sbt._

object LibraryVersions {

  val http4sVersion = "0.20.1"
  val dropWizardMetricsVersion = "4.1.0"

  val typesafeConfig = "com.typesafe" % "config" % "1.3.4"

  val catsEffect = "org.typelevel" %% "cats-effect" % "1.3.0"

  val http4sCore = "org.http4s" %% "http4s-dsl" % http4sVersion
  val http4sServer = "org.http4s" %% "http4s-blaze-server" % http4sVersion
  val http4sDropwizardMetrics = "org.http4s" %% "http4s-dropwizard-metrics" % http4sVersion

  val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
  val journal = "io.verizon.journal" %% "core" % "3.0.19"

  val metricsCore = "io.dropwizard.metrics" % "metrics-core" % dropWizardMetricsVersion
  val metricsHealthChecks = "io.dropwizard.metrics" % "metrics-healthchecks" % dropWizardMetricsVersion
  val metricsJson = "io.dropwizard.metrics" % "metrics-json" % dropWizardMetricsVersion
  val metricsJvm = "io.dropwizard.metrics" % "metrics-jvm" % dropWizardMetricsVersion

  //test
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
}