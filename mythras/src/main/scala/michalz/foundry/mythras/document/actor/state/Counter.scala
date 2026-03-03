package michalz.foundry.mythras.document.actor.state

import scala.scalajs.js

import michalz.foundry.mythras.document.actor.data.CounterWithMod

class Counter(source: CounterWithMod, calculatedMax: Int) extends js.Object:
  def max: Int     = calculatedMax + source.mod
  def baseMax: Int = calculatedMax
  def current: Int = source.current
  def mod: Int     = source.mod
