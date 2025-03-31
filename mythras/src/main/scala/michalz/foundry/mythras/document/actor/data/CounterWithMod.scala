package michalz.foundry.mythras.document.actor.data

import scala.scalajs.js

trait CounterWithMod extends js.Object:
  def max: Int
  def current: Int
  def mod: Int

object CounterWithMod:
  def apply(source: CounterWithMod): CounterWithMod = new CounterWithMod:
    override def max: Int = source.max
    override def current: Int = source.current
    override def mod: Int = source.mod
    def maxValue: Int = max + mod
