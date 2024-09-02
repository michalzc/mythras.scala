package foundry.client

import scala.scalajs.js

@js.native
trait I18n extends js.Object {
  def localize(value: String): String = js.native
}

@js.native
trait Game extends js.Object {
  val i18n: I18n = js.native
}
