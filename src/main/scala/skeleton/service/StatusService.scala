package skeleton.service

import java.time.{Instant, LocalDateTime, ZoneId}

import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import skeleton.BuildInfo
import skeleton.status.Uptime

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
  }

}
