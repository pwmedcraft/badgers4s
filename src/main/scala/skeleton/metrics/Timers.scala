package skeleton.metrics

import java.util.concurrent.TimeUnit

import cats.effect.IO
import com.codahale.metrics.{MetricRegistry, Timer}

import scala.concurrent.duration.FiniteDuration

class Timers(registry: MetricRegistry) {

  val helloWorld: Timer = registry.timer("hello-world")

}

object Timers {

  //NOTE: timer includes a 'count' field, so you can use timers to keep track of the number of calls too
  def time[A](timer: Timer, io: IO[A]): IO[A] =
    (for {
      start <- IO{System.nanoTime()}
      result <- io
      end <- IO{System.nanoTime()}
    } yield (result, FiniteDuration(end - start, TimeUnit.NANOSECONDS)))
      .map { case (response, t) =>
        timer.update(t.length, t.unit)
        response
      }

}