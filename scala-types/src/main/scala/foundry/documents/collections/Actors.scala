package foundry.documents.collections

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

import foundry.client.apps.ActorSheetParams

@js.native
@JSGlobal("foundry.documents.collections.Actors")
object Actors extends js.Object {
  def registerSheet(
      module: String,
      sheetClass: js.Dynamic,
      params: ActorSheetParams = js.native
  ): js.Any = js.native
  def unregisterSheet(module: String, sheetClass: js.Dynamic): js.Any =
    js.native
}
