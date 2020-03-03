package badgers4s.status

import java.lang.management.ManagementFactory._
import java.util.concurrent.TimeUnit

import scala.concurrent.duration.FiniteDuration

object Uptime {

  def uptime: String = {
    val u = FiniteDuration(getRuntimeMXBean.getUptime, TimeUnit.MILLISECONDS)
    s"${u.toDays} days, ${u.toHours % 24} hours, ${u.toMinutes % 60} minutes and ${u.toSeconds % 60} seconds"
  }

}
