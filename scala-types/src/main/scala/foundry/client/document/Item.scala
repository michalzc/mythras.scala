package foundry.client.document

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSName}
import scala.scalajs.js.|

@js.native
@JSGlobal
class Item[SystemData <: js.Object](data: ItemData[SystemData], context: js.Object) extends Document[SystemData] with ClientDocument:
  def getRollData(): js.Object = js.native
