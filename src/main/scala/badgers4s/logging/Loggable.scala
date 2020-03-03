package badgers4s.logging

import journal.Logger

trait Loggable {
  val logger = Logger(this.getClass.getName)
}
