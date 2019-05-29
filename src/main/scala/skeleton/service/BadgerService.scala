package skeleton.service

import java.time.LocalDate

import cats.effect.IO
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._
import skeleton.metrics.Timers
import skeleton.metrics.Timers.time
import skeleton.model.{Badger, Berries, HedgehogsFeet, Nuts, Seeds}

class BadgerService(timers: Timers) {

  val sett = Map(
    "Keith"    -> Badger("Keith", Some(LocalDate.of(2017, 7, 14)), List(Nuts, HedgehogsFeet)),
    "Geoffrey" -> Badger("Geoffrey", Some(LocalDate.of(2015, 10, 27)), List(Berries, Seeds, HedgehogsFeet)),
    "Barbara"  -> Badger("Barbara", Some(LocalDate.of(2016, 3, 2)), List(HedgehogsFeet))
  )

  def service: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root => time(timers.allBadgers, Ok(sett.values.asJson))
    case GET -> Root / badgerName => time(timers.individualBadger, sett.get(badgerName).fold(NotFound())(badger => Ok(badger.asJson)))
  }

}
