package foundry.client.document

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import scala.annotation.nowarn

@js.native
@JSGlobal
@nowarn
class Item[SystemData <: js.Object](
    data: ItemData[SystemData],
    context: js.Object
) extends Document[SystemData]
    with ClientDocument:
  def getRollData(): js.Object = js.native
