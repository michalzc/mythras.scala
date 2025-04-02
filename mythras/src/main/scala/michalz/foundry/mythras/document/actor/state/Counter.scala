package michalz.foundry.mythras.document.actor.state

import michalz.foundry.mythras.document.actor.data.CounterWithMod

import scala.scalajs.js

class Counter(source: CounterWithMod) extends js.Object:
  def max: Int     = source.max + source.mod
  def baseMax: Int = source.max
  def current: Int = source.current
  def mod: Int     = source.mod

object Counter:
  def apply(source: CounterWithMod): Counter = new Counter(source)
