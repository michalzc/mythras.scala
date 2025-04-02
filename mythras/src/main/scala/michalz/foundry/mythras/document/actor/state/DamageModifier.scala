package michalz.foundry.mythras.document.actor.state

import michalz.foundry.mythras.document.actor.data.FieldWithMod
import michalz.foundry.mythras.utils.DamageModifierService

class DamageModifier(source: FieldWithMod) extends Field(source):
  val damageModValue: Int = source.mod
  val damageMod: String   = DamageModifierService.modByStep(damageModValue)

object DamageModifier
