package badgers4s.service

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import badgers4s.metrics.Timers
import badgers4s.metrics.Timers.time

class RootService(timers: Timers) {

  def service: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root => time(timers.helloWorld, Ok("Hello World"))
  }

}
