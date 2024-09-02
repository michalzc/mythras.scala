package michalz.foundry.mythras

import org.scalajs.dom

import scala.scalajs.js

package object utils {
  def log(message: String, params: Any*): js.Any = dom.console.log(s"Dominion Rules | ${message}", params*)
  def logObject[T](obj: T, message: String): T = {
    log(message, obj)
    obj
  }
}
