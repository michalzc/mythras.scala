package michalz.foundry.mythras.application

import foundry.client.FoundryUtils.mergeObject
import foundry.client.apps.data.ApplicationOptions
import foundry.client.apps.{ActorSheetOptions, TabDef}
import michalz.foundry.mythras.application.data.CharacterSheetData
import michalz.foundry.mythras.document.actor.MythrasActor
import michalz.foundry.mythras.document.actor.data.MythrasCharacterData
import michalz.foundry.mythras.utils.{log, logObject}

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.JSExportStatic

class MythrasCharacterSheet(actor: MythrasActor[MythrasCharacterData with js.Object], options: ApplicationOptions)
    extends MythrasBaseSheet(actor, options):
  override def template: String = "systems/mythras-scala/templates/character-sheet.hbs"

  override def getData(options: js.Object = js.Object()): DataType =
    val context   = super.getData(options)
    val sheetData = new CharacterSheetData()

    log("Actor system", actor.system)
    actor.system.foreach(system => log("Charactersitics", system.characteristics))
    logObject(mergeObject(context, sheetData), "Character Sheet Context Data")

object MythrasCharacterSheet:
  @JSExportStatic
  def name: String = "Character"

  @JSExportStatic
  def defaultOptions: ActorSheetOptions =
    val defaultOptions = MythrasBaseSheet.defaultOptions
    val newOptions: ActorSheetOptions = new ActorSheetOptions {
      override val tabs: js.Array[TabDef] = js.Array(
        new TabDef {
          override val navSelector: String     = ".sheet-tabs"
          override val contentSelector: String = ".sheet-body"
          override val initial: String         = ".notes"
        },
      )
      override val classes: UndefOr[js.Array[String]] = js.Array("mythras", "sheet", "actor", "character")
    }

    mergeObject(defaultOptions, newOptions)
