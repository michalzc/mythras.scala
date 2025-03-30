package michalz.foundry.mythras.document.actor.data

import scala.scalajs.js

trait CounterWithMod extends js.Object:
  def max: Int
  def current: Int
  def mod: Int
