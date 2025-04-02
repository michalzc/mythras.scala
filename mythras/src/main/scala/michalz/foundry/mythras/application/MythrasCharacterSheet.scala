package michalz.foundry.mythras.application

import foundry.client.FoundryUtils.mergeObject
import foundry.client.apps.data.ApplicationOptions
import foundry.client.apps.utils.ApplicationHeaderButton
import foundry.client.apps.{ActorSheetOptions, TabDef}
import michalz.foundry.mythras.{Const, LocKey}
import michalz.foundry.mythras.document.actor.MythrasActor
import michalz.foundry.mythras.document.actor.data.MythrasCharacterDataModel
import michalz.foundry.mythras.utils.log

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.JSExportStatic
import scala.scalajs.js.JSConverters.*

class MythrasCharacterSheet(actor: MythrasActor[MythrasCharacterDataModel], options: ApplicationOptions)
    extends MythrasBaseSheet(actor, options):
  override def template: String = "systems/mythras-scala/templates/sheet/actor/character-sheet.hbs"

  override def _getHeaderButtons(): js.Array[js.Object] =
    val buttons = super._getHeaderButtons().toList
    (ApplicationHeaderButton("Characer", "character", "ci ci-character", js.Any.fromFunction0(showCharacterDialog)) :: buttons).toJSArray

  override def getData(options: js.Object): DataType =
    val context = super.getData(options)

    object AdditionalData extends js.Object:
      val tabs: js.Array[LocKey] = Const.Sheets.Actor.CharacterSheet.tabs

    mergeObject(context, AdditionalData)

  private def showCharacterDialog(): Unit =
    log("Showing character dialog")

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
          override val initial: String         = ".character"
        },
      )
      override val classes: UndefOr[js.Array[String]] = js.Array("mythras", "sheet", "actor", "character")
      override val width: Int                         = 1200
      override val height: Int                        = 1200
    }

    mergeObject(defaultOptions, newOptions)
