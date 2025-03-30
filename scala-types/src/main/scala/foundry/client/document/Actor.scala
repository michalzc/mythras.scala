package foundry.client.document

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal
class Actor[SystemData <: js.Object](data: ActorData[SystemData], context: js.Object) extends Document[SystemData]
