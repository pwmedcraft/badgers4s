package skeleton.logging

import journal.Logger

trait Loggable {
  val logger = Logger(this.getClass.getName)
}
