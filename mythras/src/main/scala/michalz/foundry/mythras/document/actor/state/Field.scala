package michalz.foundry.mythras.document.actor.state

import scala.scalajs.js

import michalz.foundry.mythras.document.actor.data.FieldWithMod

class Field(source: FieldWithMod) extends js.Object:
  lazy val value: Int = source.base + source.mod
  lazy val mod: Int   = source.mod
  lazy val base: Int  = source.base
