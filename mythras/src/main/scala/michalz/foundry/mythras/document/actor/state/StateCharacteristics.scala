package michalz.foundry.mythras.document.actor.state

import michalz.foundry.mythras.Const
import michalz.foundry.mythras.document.actor.data.Characteristics

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

class StateCharacteristics(val source: Characteristics) extends js.Object:
  lazy val strength: FieldWithLabel     = FieldWithLabel(Const.CharacteristicsKeys.strength, source.strength)
  lazy val constitution: FieldWithLabel = FieldWithLabel(Const.CharacteristicsKeys.constitution, source.constitution)
  lazy val size: FieldWithLabel         = FieldWithLabel(Const.CharacteristicsKeys.size, source.size)
  lazy val dexterity: FieldWithLabel    = FieldWithLabel(Const.CharacteristicsKeys.dexterity, source.dexterity)
  lazy val intelligence: FieldWithLabel = FieldWithLabel(Const.CharacteristicsKeys.intelligence, source.intelligence)
  lazy val power: FieldWithLabel        = FieldWithLabel(Const.CharacteristicsKeys.power, source.power)
  lazy val charisma: FieldWithLabel     = FieldWithLabel(Const.CharacteristicsKeys.charisma, source.charisma)

  lazy val elements: js.Array[FieldWithLabel] = List(strength, constitution, size, dexterity, intelligence, power, charisma).toJSArray
