package foundry.client

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal
object Actors extends js.Object {
  def registerSheet(module: String, sheetClass: js.Dynamic, params: ActorSheetParams = js.native): js.Any = js.native
  def unregisterSheet(module: String, sheetClass: js.Dynamic): js.Any = js.native
}
