package foundry.client.document

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import scala.annotation.nowarn

@js.native
@JSGlobal
@nowarn
class Actor[SystemData <: js.Object](
    data: ActorData[SystemData],
    context: js.Object
) extends Document[SystemData]
    with ClientDocument:
  def items: js.Iterable[Item[?]] = js.native
  def getRollData(): js.Object    = js.native
  def applyActiveEffects(): Unit  = js.native
