package michalz.foundry.mythras.document.actor.state

import michalz.foundry.mythras.document.actor.MythrasActor
import michalz.foundry.mythras.document.actor.data.MythrasActorDataModel
import michalz.foundry.mythras.document.actor.data.MythrasCharacterDataModel

class CharacterState(actor: MythrasActor[MythrasCharacterDataModel]) extends ActorState(actor)

object CharacterState:
  def apply[DataModel <: MythrasActorDataModel](
      actor: MythrasActor[DataModel]
  ): CharacterState =
    new CharacterState(
      actor.asInstanceOf[MythrasActor[MythrasCharacterDataModel]]
    )
