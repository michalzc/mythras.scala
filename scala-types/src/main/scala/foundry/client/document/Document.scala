package foundry.client.document

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.|

@js.native
trait Document[DataModel <: js.Object] extends js.Object {
  @JSName("type")
  val `type`: String             = js.native
  val id: String                 = js.native
  var name: String               = js.native
  val system: DataModel         = js.native
  val folder: js.UndefOr[String] = js.native
}
