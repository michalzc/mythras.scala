package michalz.foundry.mythras.application.data

import foundry.client.GlobalFunctions.game.i18n.localize
import michalz.foundry.mythras.document.actor.data.{Characteristics, Consts, MythrasCharacterData}
import michalz.foundry.mythras.utils.log

import scala.scalajs.js
import scala.scalajs.js.JSConverters._

class Skill(
    val id: String,
    val name: String,
    val value: Int,
) extends js.Object

class AttributeWithSkill(
    val name: String,
    val label: String,
    val fieldName: String,
    val value: Int,
    val skills: js.Array[Skill],
) extends js.Object


class SheetData(
    val attributes: js.Array[AttributeWithSkill],
    val characteristics: Characteristics,
    val luck: Int
) extends js.Object

class CharacterSheetData (
//  val system: js.UndefOr[js.Object]    = js.undefined,
  val sheetData: js.UndefOr[SheetData] = js.undefined
) extends js.Object

object SheetData {
  def fromSystem(characterData: MythrasCharacterData): SheetData = {

    val systemAttributes = characterData.attributes.asInstanceOf[js.Dictionary[Int]].toMap
    val attributes = Consts.attributes
      .flatMap(key => systemAttributes.get(key).map(value => key -> value))
      .map { case (attr, value) =>
        new AttributeWithSkill(
          name = attr,
          label = localize(s"dominion.attributes.$attr"),
          fieldName = s"system.attributes.$attr",
          value = value,
          skills = js.Array(),
        )
      }

    new SheetData(attributes.toJSArray, characterData.characteristics, characterData.attributes.luck)
  }

}
