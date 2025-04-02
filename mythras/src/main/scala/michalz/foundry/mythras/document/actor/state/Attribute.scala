package michalz.foundry.mythras.document.actor.state

import michalz.foundry.mythras.document.actor.data.AttributeWithMod

import scala.scalajs.js

class Attribute(source: AttributeWithMod, calculated: Int) extends js.Object:
  val value: Int = calculated + source.mod
