package foundry.client.document

import foundry.documents.{ActorData, Document}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSName}
import scala.scalajs.js.|

@js.native
@JSGlobal
class Actor[SystemData <: js.Object](data: ActorData[SystemData], context: js.Object) extends Document[SystemData]
