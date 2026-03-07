package michalz.foundry.mythras.application
import scala.annotation.nowarn
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

import foundry.applications.api.ApplicationRenderContext
import foundry.applications.api.ApplicationRenderOptions
import foundry.applications.sheets.ActorSheetV2
import foundry.applications.sheets.DocumentSheetOptionsV2
import foundry.applications.sheets.DocumentSheetRenderOptions

import org.scalajs.dom.HTMLElement
import scalatags.JsDom.all.*

import michalz.foundry.mythras.document.actor.MythrasActor
import michalz.foundry.mythras.document.actor.data.MythrasNPCDataModel
import michalz.foundry.mythras.utils.log

class MythrasNPCSheet(
    options: DocumentSheetOptionsV2[MythrasNPCDataModel, MythrasActor[MythrasNPCDataModel]]
    // actor: MythrasActor[MythrasNPCDataModel & js.Object],
    // options: ApplicationOptions
) extends ActorSheetV2[MythrasNPCDataModel, MythrasActor[MythrasNPCDataModel]](options) {
// ) extends MythrasBaseSheet(actor, options) {
//  type SheetData = ActorSheetData[MythrasNPCData, MythrasActor[MythrasNPCData]]

  // override def template: String =
  // "systems/mythras-scala/templates/sheet/actor/npc-sheet.hbs"

  // override def getData(options: js.Object): DataType = {
  //   val context = super.getData(options)
  //   log("Data for NPC Sheet")
  //   log("Actor", actor)
  //   log("Context", context)
  //   log("Systemy", actor.system, context)

  //   context
  // }
  override def _renderHTML(
      context: ApplicationRenderContext,
      options: ApplicationRenderOptions & DocumentSheetRenderOptions
  ): js.Promise[HTMLElement] = {
    log("_renderHTML")
    log("context", context)
    log("options", options)
    js.Promise.resolve(div("Działa").render)
  }

  def _replaceHTML(result: HTMLElement, element: HTMLElement, options: js.Any): Unit = {
    log("_replaceHTML")
    log("result", result)
    log("element", element)
    log("options", options)
    element.append(result)
  }
}

object MythrasNPCSheet {

  @JSExportStatic
  def name: String = "NPC"

  @JSExportStatic
  @nowarn
  val DEFAULT_OPTIONS: js.Object = new js.Object {

    val classes  = js.Array("mythras", "sheet", "actor", "npc")
    val position = new js.Object {
      val width  = 900
      val height = 600
    }
  }
}
