package michalz.foundry.mythras.document.actor

import foundry.client.document.Actor
import foundry.client.document.ActorData
import michalz.foundry.mythras.Const
import michalz.foundry.mythras.document.actor.data.MythrasActorDataModel
import michalz.foundry.mythras.document.actor.state.ActorState
import michalz.foundry.mythras.document.actor.state.CharacterState
import michalz.foundry.mythras.document.actor.state.NPCState
import michalz.foundry.mythras.document.state.StateHolder
import michalz.foundry.mythras.utils.log

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("MythrasActor")
class MythrasActor[DataModel <: MythrasActorDataModel](
    data: ActorData[DataModel],
    context: js.Object
) extends Actor[DataModel](data, context):

  private val internalState: js.UndefOr[StateHolder[? <: ActorState[? <: MythrasActorDataModel]]] =
    StateHolder(
      buildState()
    )

  def state: js.UndefOr[ActorState[? <: MythrasActorDataModel]] =
    internalState.map(_.get)

  private def buildState(): ActorState[? <: MythrasActorDataModel] =
    `type` match
      case Const.ActorTypes.character.key => CharacterState(this)
      case Const.ActorTypes.npc.key       => NPCState(this)

  override def getRollData(): js.Object = {
    log(s"Getting roll data for $name")
    val context = super.getRollData()
    context
  }

  override def prepareData(): Unit = {
    super.prepareData()
    internalState.foreach(_.invalidate())
  }

  log(s"Creating new actor ${data.name}", data)

object MythrasActor {

  @JSExportStatic
  def name: String = "MythrasActor"
}
