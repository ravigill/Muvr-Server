package io.muvr

import akka.util.Timeout

object Timeouts {
  import scala.concurrent.duration._

  object defaults {
    implicit val defaultTimeout = Timeout(3.seconds)
  }

}
