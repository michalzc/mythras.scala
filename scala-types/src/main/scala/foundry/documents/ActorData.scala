package foundry.documents

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.|

@js.native
trait ActorData[SystemData <: js.Object] extends js.Object {
  val _id: String = js.native
  var name: String = js.native
  @JSName("type")
  val `type`: String = js.native
  val system: SystemData = js.native
  val folder: String | Null = js.native
}
