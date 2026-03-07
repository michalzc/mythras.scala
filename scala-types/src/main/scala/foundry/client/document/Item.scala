package foundry.client.document

import scala.annotation.nowarn
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal
@nowarn
class Item[SystemData <: js.Object](
    data: ItemData[SystemData],
    context: js.Object
) extends Document[SystemData]
    with ClientDocument:
  def getRollData(): js.Object = js.native
