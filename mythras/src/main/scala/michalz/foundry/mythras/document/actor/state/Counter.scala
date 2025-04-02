package michalz.foundry.mythras.document.actor.state

import michalz.foundry.mythras.document.actor.data.CounterWithMod

import scala.scalajs.js

class Counter(source: CounterWithMod, calculatedMax: Int) extends js.Object:
  def max: Int     = calculatedMax + source.mod
  def baseMax: Int = calculatedMax
  def current: Int = source.current
  def mod: Int     = source.mod
