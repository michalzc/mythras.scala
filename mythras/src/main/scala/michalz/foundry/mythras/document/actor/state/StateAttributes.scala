package michalz.foundry.mythras.document.actor.state

import michalz.foundry.mythras.document.actor.data.Attributes

import scala.scalajs.js

class StateAttributes(attributes: Attributes) extends js.Object:
  val actionPoints: Counter          = Counter(attributes.actionPoints)
  val damageModifier: DamageModifier = DamageModifier(attributes.damageModifier)
