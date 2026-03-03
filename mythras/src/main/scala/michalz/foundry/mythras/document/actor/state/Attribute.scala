package michalz.foundry.mythras.document.actor.state

import scala.scalajs.js

import michalz.foundry.mythras.document.actor.data.AttributeWithMod

class Attribute(source: AttributeWithMod, calculated: Int) extends js.Object:
  val value: Int = calculated + source.mod
