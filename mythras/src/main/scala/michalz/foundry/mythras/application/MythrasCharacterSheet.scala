package michalz.foundry.mythras.application

import foundry.client.FoundryUtils.mergeObject
import foundry.client.{ActorSheetOptions, TabDef}
import michalz.foundry.mythras.application.data.{CharacterSheetData, SheetData}
import michalz.foundry.mythras.document.actor.MythrasActor
import michalz.foundry.mythras.document.actor.data.MythrasCharacterData
import michalz.foundry.mythras.utils.logObject

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.JSExportStatic

class MythrasCharacterSheet(actor: MythrasActor[MythrasCharacterData], options: js.Dictionary[js.Any] = js.Dictionary.empty) extends MythrasBaseSheet(actor, options) {
  override def template: String = "systems/mythras-scala/templates/character-sheet.hbs"

  override def getData(options: js.Object = js.Object()): js.Object = {
    val context = super.getData()

    val sheetData = new CharacterSheetData(
//      system = actor.system,
//      sheetData = SheetData.fromSystem(actor.system),
    )

    logObject(mergeObject(context, sheetData), "Character Sheet Context Data")
  }
}

object MythrasCharacterSheet {
  @JSExportStatic
  def name: String = "Character"

  @JSExportStatic
  def defaultOptions: ActorSheetOptions = {
    val defaultOptions = MythrasBaseSheet.defaultOptions
    val newOptions = new ActorSheetOptions {
      override val tabs = js.Array(
        new TabDef {
          override val navSelector    : String = ".sheet-tabs"
          override val contentSelector: String = ".sheet-body"
          override val initial        : String = ".notes"
        }
      )
      override val classes: UndefOr[js.Array[String]] = js.Array("mythras", "sheet", "actor", "character")
    }

    mergeObject(defaultOptions, newOptions)
  }
}
