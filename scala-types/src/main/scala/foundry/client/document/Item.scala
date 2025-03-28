package foundry.client.document

import foundry.documents.{ActorData, ItemData}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSName}
import scala.scalajs.js.|

@js.native
@JSGlobal
class Item[SystemData <: js.Object](data: ItemData[SystemData], context: js.Object) extends js.Object {
  val _id   : String        = js.native
  var name  : String        = js.native
  @JSName("type")
  val `type`: String        = js.native
  val system: SystemData    = js.native
  val folder: String | Null = js.native
}
