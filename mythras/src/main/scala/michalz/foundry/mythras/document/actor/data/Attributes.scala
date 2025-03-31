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

  def apply(source: Attributes): Attributes = new Attributes:
    override def actionPoints: CounterWithMod     = CounterWithMod(source.actionPoints)
    override def damageModifier: FieldWithMod     = FieldWithMod(source.damageModifier)
    override def experienceModifier: FieldWithMod = FieldWithMod(source.experienceModifier)
    override def healingRate: FieldWithMod        = FieldWithMod(source.healingRate)
    override def initiative: FieldWithMod         = FieldWithMod(source.initiative)
    override def luckPoints: CounterWithMod       = CounterWithMod(source.luckPoints)
    override def movementRate: FieldWithMod       = FieldWithMod(source.movementRate)
    override def magicPoints: MagicPointsCounter  = MagicPointsCounter(source.magicPoints)

  trait MagicPointsCounter extends js.Object:
    def max: Int
    def current: Int
    def mod: Int
    def active: Int

  object MagicPointsCounter:
    def apply(source: MagicPointsCounter): MagicPointsCounter = new MagicPointsCounter:
      override def max: Int     = source.max
      override def current: Int = source.current
      override def mod: Int     = source.mod
      override def active: Int  = source.active
      def maxValue: Int         = max + mod - active
