package michalz.foundry.mythras.document.actor.data

import scala.scalajs.js

trait Attributes extends js.Object:
  def actionPoints: CounterWithMod
  def damageModifier: AttributeWithMod
  def experienceModifier: AttributeWithMod
  def healingRate: AttributeWithMod
  def initiative: AttributeWithMod
  def luckPoints: CounterWithMod
  def movementRate: FieldWithMod
  def magicPoints: CounterWithMod
