package michalz.foundry.mythras.document.actor.state

import michalz.foundry.mythras.document.actor.data.AttributeWithMod
import michalz.foundry.mythras.services.DamageModifierService

class DamageModifier(source: AttributeWithMod, strength: Int, size: Int)
    extends Attribute(
      source,
      DamageModifierService.valueToStep(strength + size)
    ):
  val damageMod: String = DamageModifierService.modByStep(value)

object DamageModifier
