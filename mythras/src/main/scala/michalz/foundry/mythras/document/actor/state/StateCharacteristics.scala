package michalz.foundry.mythras.document.actor.state

import michalz.foundry.mythras.document.actor.data.{Characteristics, FieldWithMod}

import scala.scalajs.js

class StateCharacteristics(val source: Characteristics) extends js.Object:
  lazy val elements: js.Array[FieldWithLabel] =
    js.Object.entries(source.asInstanceOf[js.Dictionary[FieldWithMod]]).map(entry => FieldWithLabel(entry._1, entry._2))
