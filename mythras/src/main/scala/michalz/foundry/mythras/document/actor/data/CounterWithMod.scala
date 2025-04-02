package michalz.foundry.mythras.document.actor.data

import scala.scalajs.js

trait CounterWithMod extends js.Object:
  def current: Int
  def mod: Int

object CounterWithMod:
  def apply(source: CounterWithMod): CounterWithMod = new CounterWithMod:
    override def current: Int = source.current
    override def mod: Int     = source.mod
