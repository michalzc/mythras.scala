package michalz.foundry.mythras.document.actor.state

import michalz.foundry.mythras.document.actor.MythrasActor
import michalz.foundry.mythras.document.actor.data.{
  MythrasActorDataModel,
  MythrasNPCDataModel
}

class NPCState(actor: MythrasActor[MythrasNPCDataModel])
    extends ActorState(actor)

object NPCState:
  def apply[DataModel <: MythrasActorDataModel](
      actor: MythrasActor[DataModel]
  ): NPCState =
    new NPCState(actor.asInstanceOf[MythrasActor[MythrasNPCDataModel]])
