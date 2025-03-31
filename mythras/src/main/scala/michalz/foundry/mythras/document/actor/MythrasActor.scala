package michalz.foundry.mythras.document.actor

import foundry.client.document.{Actor, ActorData}
import michalz.foundry.mythras.Const
import michalz.foundry.mythras.document.actor.data.MythrasActorDataModel
import michalz.foundry.mythras.document.actor.state.{ActorState, CharacterState, NPCState}
import michalz.foundry.mythras.document.state.StateHolder
import michalz.foundry.mythras.utils.log

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportStatic, JSExportTopLevel}

@JSExportTopLevel("MythrasActor")
class MythrasActor[DataModel <: MythrasActorDataModel](data: ActorData[DataModel], context: js.Object)
    extends Actor[DataModel](data, context):

  val internalState: StateHolder[? <: ActorState[? <: MythrasActorDataModel]] = StateHolder(buildState())

  def getState: ActorState[? <: MythrasActorDataModel] = internalState.get

  def buildState(): ActorState[? <: MythrasActorDataModel] = `type` match
    case Const.ActorTypes.character.key => CharacterState(this)
    case Const.ActorTypes.npc.key       => NPCState(this)

  override def getRollData(): js.Object = {
    log(s"Getting roll data for $name")
    val context = super.getRollData()
    context
  }

  log(s"Creating new actor ${data.name}", data)

object MythrasActor {

  @JSExportStatic
  def name: String = "MythrasActor"
}
