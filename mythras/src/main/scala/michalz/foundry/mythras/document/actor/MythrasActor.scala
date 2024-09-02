package michalz.foundry.mythras.document.actor

import foundry.client.Actor
import foundry.documents.ActorData
import michalz.foundry.mythras.document.actor.data.MythrasActorData

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportStatic, JSExportTopLevel}

@JSExportTopLevel("MythrasActor")
class MythrasActor[SD <: MythrasActorData](data: ActorData[SD], context: js.Object) extends Actor[SD](data, context)

object MythrasActor {
  @JSExportStatic
  def name: String = "MythrasActor"
}
