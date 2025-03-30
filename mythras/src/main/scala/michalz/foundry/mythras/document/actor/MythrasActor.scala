package michalz.foundry.mythras.document.actor

import foundry.client.document.{Actor, ActorData}
import michalz.foundry.mythras.utils.log

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportStatic, JSExportTopLevel}

@JSExportTopLevel("MythrasActor")
class MythrasActor[DataModel <: js.Object](data: ActorData[DataModel], context: js.Object) extends Actor[DataModel](data, context):
  log(s"Creating new actor ${data.name}", data)

object MythrasActor {

  @JSExportStatic
  def name: String = "MythrasActor"
}
