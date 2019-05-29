package skeleton.service

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._
import skeleton.metrics.Timers
import skeleton.metrics.Timers.time

class RootService(timers: Timers) {

  def service: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root => time(timers.helloWorld, Ok("Hello World"))
  }

}
