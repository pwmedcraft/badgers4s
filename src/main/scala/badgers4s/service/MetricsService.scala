package badgers4s.service

import java.util.concurrent.TimeUnit

import cats.effect.IO
import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.json.MetricsModule
import com.fasterxml.jackson.databind.ObjectMapper
import org.http4s.{EntityEncoder, HttpRoutes}
import org.http4s.dsl.io._

object MetricsService {
  private val defaultMapper = {
    val module = new MetricsModule(TimeUnit.SECONDS, TimeUnit.SECONDS, true)
    new ObjectMapper().registerModule(module)
  }

  private def metricRegistryEncoder(mapper: ObjectMapper = defaultMapper): EntityEncoder[IO, MetricRegistry] =
    EntityEncoder[IO, String].contramap { metricRegistry =>
      val writer = mapper.writerWithDefaultPrettyPrinter()
      writer.writeValueAsString(metricRegistry)
    }

  private implicit val encoder = metricRegistryEncoder()

  def metricsResponse(registry: MetricRegistry) = Ok(registry)
}

class MetricsService(metricRegistry: MetricRegistry) {
  def service: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root => MetricsService.metricsResponse(metricRegistry)
  }
}