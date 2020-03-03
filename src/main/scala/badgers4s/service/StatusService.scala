package badgers4s.service

import java.time.{Instant, LocalDateTime, ZoneId}

import cats.effect.IO
import io.circe.Json
import io.circe.java8.time._
import io.circe.syntax._
import org.http4s.HttpRoutes
import org.http4s.circe._
import org.http4s.dsl.io._
import badgers4s.BuildInfo
import badgers4s.status.Uptime

object StatusService {

  def service: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "version" => Ok(BuildInfo.version)
    case GET -> Root => Ok(
      s"""
         |app name\t${BuildInfo.name}
         |version\t\t${BuildInfo.version}
         |commit\t\t${BuildInfo.commitHash.getOrElse("n/a")}
         |build time\t${LocalDateTime.ofInstant(Instant.ofEpochMilli(BuildInfo.builtAtMillis), ZoneId.systemDefault())}
         |scalaVersion\t${BuildInfo.scalaVersion}
         |timestamp\t${LocalDateTime.now()}
         |uptime\t\t${Uptime.uptime}
         |
       """.stripMargin
    )
    case GET -> Root / "json" => Ok(Json.obj(
      ("appName", Json.fromString(BuildInfo.name)),
      ("version", Json.fromString(BuildInfo.version)),
      ("commit", BuildInfo.commitHash.map(Json.fromString).getOrElse(Json.Null)),
      ("buildTime", LocalDateTime.ofInstant(Instant.ofEpochMilli(BuildInfo.builtAtMillis), ZoneId.systemDefault()).asJson),
      ("scalaVersion", Json.fromString(BuildInfo.scalaVersion)),
      ("timestamp", Json.fromString(LocalDateTime.now().toString)),
      ("uptime", Json.fromString(Uptime.uptime)),
    ))
  }

}
