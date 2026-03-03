package michalz.foundry.mythras

import scala.scalajs.js

import org.scalajs.dom

package object utils {
  inline def log(message: String, params: Any*): js.Any =
    dom.console.log(s"${Const.MODULE_TITLE} | $message", params*)
  inline def logObject[T](obj: T, message: String): T = {
    log(message, obj)
    obj
  }
}
