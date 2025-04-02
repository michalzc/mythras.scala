package michalz.foundry.mythras.document.actor.data

import scala.scalajs.js

trait FieldWithMod extends js.Object:
  def base: Int
  def mod: Int

object FieldWithMod:
  def apply(source: FieldWithMod): FieldWithMod = new FieldWithMod:
    override def base: Int = source.base
    override def mod: Int  = source.mod
    def value: Int         = base + mod
