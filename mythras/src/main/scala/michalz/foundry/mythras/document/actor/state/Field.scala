package michalz.foundry.mythras.document.actor.state

import michalz.foundry.mythras.document.actor.data.FieldWithMod

import scala.scalajs.js

class Field(source: FieldWithMod) extends js.Object:
  lazy val value: Int = source.base + source.mod
  lazy val mod: Int   = source.mod
  lazy val base: Int  = source.base
