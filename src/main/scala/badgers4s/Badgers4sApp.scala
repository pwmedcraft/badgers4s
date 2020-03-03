package badgers4s

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import com.typesafe.config.ConfigFactory
import org.http4s.implicits._
import org.http4s.metrics.dropwizard.Dropwizard
import org.http4s.server.{HttpMiddleware, Router}
import org.http4s.server.blaze._
import org.http4s.server.middleware.Metrics
import badgers4s.config.{ApplicationConfiguration, Environment}
import badgers4s.logging.Loggable
import badgers4s.metrics.{MetricRegistryWithJvmGauges, Timers}
import badgers4s.service.{BadgerService, MetricsService, RootService, StatusService}

import scala.concurrent.duration._

object Badgers4sApp extends IOApp with Loggable {

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

    val metricRegistry = MetricRegistryWithJvmGauges.apply
    val timers = new Timers(metricRegistry)
    val timingMiddleware: HttpMiddleware[IO] = Metrics.apply(Dropwizard(metricRegistry, "badgers4s"))

    BlazeServerBuilder[IO]
      .withResponseHeaderTimeout(3 minutes)
      .withIdleTimeout(3 minutes)
      .bindHttp(config.port, "0.0.0.0")
      .withHttpApp(Router(
        "/status" -> StatusService.service,
        "/metrics" -> new MetricsService(metricRegistry).service,
        "/api/badgers" -> timingMiddleware(new BadgerService(timers).service),
        "/" -> timingMiddleware(new RootService(timers).service)
      ).orNotFound)
      .serve.compile.drain.as(ExitCode.Success)
  }

}
