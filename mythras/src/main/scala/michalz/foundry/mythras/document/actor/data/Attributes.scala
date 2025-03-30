package michalz.foundry.mythras.document.actor.data

import michalz.foundry.mythras.document.actor.data.Attributes.MagicPointsCounter

import scala.scalajs.js

trait Attributes extends js.Object:
  def actionPoints: CounterWithMod
  def damageModifier: FieldWithMod
  def experienceModifier: FieldWithMod
  def healingRate: FieldWithMod
  def initiative: FieldWithMod
  def luckPoints: CounterWithMod
  def movementRate: FieldWithMod
  def magicPoints: MagicPointsCounter

object Attributes:
  trait MagicPointsCounter extends js.Object:
    def max: Int
    def current: Int
    def mod: Int
    def active: Int
