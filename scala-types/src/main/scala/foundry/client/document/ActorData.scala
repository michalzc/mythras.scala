package foundry.client.document

import scala.scalajs.js

trait ActorData[SystemData <: js.Object] extends DocumentData[SystemData]:

  def items: js.Array[ItemData[?]]
