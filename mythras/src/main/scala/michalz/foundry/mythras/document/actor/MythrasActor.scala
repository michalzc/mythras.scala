package michalz.foundry.mythras.document.actor

import foundry.client.document.Actor
import foundry.documents.ActorData
import michalz.foundry.mythras.document.actor.MythrasActor.SystemData
import michalz.foundry.mythras.document.actor.data.MythrasActorSystemData
import michalz.foundry.mythras.utils.log

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportStatic, JSExportTopLevel}

@JSExportTopLevel("MythrasActor")
class MythrasActor[SD <: SystemData](data: ActorData[SD], context: js.Object) extends Actor[SD](data, context):
  log(s"Creating new actor ${data.name}")

object MythrasActor {

  type SystemData = MythrasActorSystemData with js.Object

  @JSExportStatic
  def name: String = "MythrasActor"
}
