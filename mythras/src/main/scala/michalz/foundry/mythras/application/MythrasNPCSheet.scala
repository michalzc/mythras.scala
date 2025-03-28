package michalz.foundry.mythras.application

import foundry.client.FoundryUtils.mergeObject
import foundry.client.apps.data.{ActorSheetData, ApplicationOptions}
import foundry.client.apps.{ActorSheetOptions, TabDef}
import michalz.foundry.mythras.document.actor.MythrasActor
import michalz.foundry.mythras.document.actor.data.MythrasNPCData
import michalz.foundry.mythras.utils.log

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.JSExportStatic

class MythrasNPCSheet(actor: MythrasActor[MythrasNPCData], options: ApplicationOptions) extends MythrasBaseSheet[MythrasNPCData](actor, options):
  type SheetData = ActorSheetData[MythrasNPCData, MythrasActor[MythrasNPCData]]

  override def template: String = "systems/mythras-scala/templates/npc-sheet.hbs"

  override def getData(options: js.Object): DataType =
    val context = super.getData(options)
    log("Data for NPC Sheet")
    log("Actor", actor)
    log("Context", context)
    log("Systemy", actor.system, context)

    context


object MythrasNPCSheet:

  @JSExportStatic
  def name: String = "NPC"

  @JSExportStatic
  def defaultOptions: ActorSheetOptions =
    val defaultOptions = MythrasBaseSheet.defaultOptions
    val newOptions = new ActorSheetOptions {
      override val tabs = js.Array(
        new TabDef {
          override val navSelector: String = ".sheet-tabs"
          override val contentSelector: String = ".sheet-body"
          override val initial: String = ".notes"
        },
      )
      override val classes: UndefOr[js.Array[String]] = js.Array("mythras", "sheet", "actor", "npc")
    }

    mergeObject(defaultOptions, newOptions)