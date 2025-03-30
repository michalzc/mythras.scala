package foundry.client.document

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.|

@js.native
trait Document[SystemData <: js.Object] extends js.Object {
  @JSName("type")
  val `type`: String                 = js.native
  val _id: String                    = js.native
  var name: String                   = js.native
  val system: js.UndefOr[SystemData] = js.native
  val folder: js.UndefOr[String]     = js.native
}
