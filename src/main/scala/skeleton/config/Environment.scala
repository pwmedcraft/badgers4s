package skeleton.config

import skeleton.logging.Loggable
import cats.syntax.either._

import scala.util.Properties

sealed trait Environment {
  val configFileName: String
  val title: String
}

case object Local extends Environment {
  override val configFileName = "local"
  override val title = "LOCAL"
}

case object Dev extends Environment {
  override val configFileName = "dev"
  override val title = "DEV"
}

object Environment extends Loggable {

  def apply(env: String): Either[String, Environment] = env match {
    case "local" => Right(Local)
    case "dev" => Right(Dev)
    case _ => Left(s"Unrecognised config value: $env")
  }

  def fromProperties: Environment = Properties.propOrNone("config").fold("No config value".asLeft[Environment])(e => Environment(e)).fold(
    s => {
      val msg =
        s"""
           |$s was specified so the system will shutdown.
           |
           |Please set system property config like so:
           |
           |  -Dconfig=ENV
           |
           |Where ENV is one of:
           |  local
           |  dev
         """.stripMargin
      logger.error(msg)
      println(msg)
      System.exit(1)
      throw new RuntimeException(msg)
    },
    identity
  )
}