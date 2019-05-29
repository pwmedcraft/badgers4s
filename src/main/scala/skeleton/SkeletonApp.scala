package skeleton

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import com.typesafe.config.ConfigFactory
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze._
import skeleton.config.{ApplicationConfiguration, Environment}
import skeleton.logging.Loggable
import skeleton.service.{RootService, StatusService}

import scala.concurrent.duration._

object SkeletonApp extends IOApp with Loggable {

  import scala.language.postfixOps

  override def run(args: List[String]): IO[ExitCode] = {

    logger.info(
      """
        |   )\.--.      .'(   )\.---.   .')       )\.---.  .-,.-.,-.    .-./(   )\  )\
        | (   ._.'  ,')\  ) (   ,-._( ( /       (   ,-._( ) ,, ,. (  ,'     ) (  \, /
        |  `-.`.   (  '/ /   \  '-,    ))        \  '-,   \( |(  )/ (  .-, (   ) \ (
        | ,_ (  \   )   (     ) ,-`    )'._.-.    ) ,-`      ) \     ) '._\ ) ( ( \ \
        |(  '.)  ) (  .\ \   (  ``-.  (       )  (  ``-.     \ (    (  ,   (   `.)/  )
        | '._,_.'   )/  )/    )..-.(   )/,__.'    )..-.(      )/     )/ ._.'      '.(
        |
      """.stripMargin
    )

    val environment = Environment.fromProperties
    val config = ApplicationConfiguration(ConfigFactory.load(environment.configFileName))

    BlazeServerBuilder[IO]
      .withResponseHeaderTimeout(3 minutes)
      .withIdleTimeout(3 minutes)
      .bindHttp(config.port, "0.0.0.0")
      .withHttpApp(Router(
        "/status" -> StatusService.service,
        "/" -> RootService.service
      ).orNotFound)
      .serve.compile.drain.as(ExitCode.Success)
  }

}
