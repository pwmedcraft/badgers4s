import sbt._

object LibraryVersions {

  val http4sVersion = "0.20.1"

  val typesafeConfig = "com.typesafe" % "config" % "1.3.4"

  val catsEffect = "org.typelevel" %% "cats-effect" % "1.3.0"

  val http4sCore = "org.http4s" %% "http4s-dsl" % http4sVersion
  val http4sServer = "org.http4s" %% "http4s-blaze-server" % http4sVersion

  val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
  val journal = "io.verizon.journal" %% "core" % "3.0.19"

  //test
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
}