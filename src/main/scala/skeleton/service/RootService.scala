package skeleton.service

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.io._

object RootService {

  def service: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root => Ok("Hello World")
  }

}
