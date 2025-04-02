package michalz.foundry.mythras.application

import foundry.client.FoundryUtils.mergeObject
import foundry.client.apps.data.ApplicationOptions
import foundry.client.apps.{ActorSheet, ActorSheetOptions}
import michalz.foundry.mythras.document.actor.MythrasActor
import michalz.foundry.mythras.document.actor.data.MythrasActorDataModel
import michalz.foundry.mythras.document.actor.state.ActorState
import michalz.foundry.mythras.utils

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import scala.scalajs.js.annotation.JSExportStatic

class MythrasBaseSheet[DataModel <: MythrasActorDataModel](actor: MythrasActor[DataModel], options: ApplicationOptions)
    extends ActorSheet[DataModel, MythrasActor[DataModel]](actor, options):
  override def getData(options: js.Object): DataType = {
    val context = super.getData(options)
    utils.logObject(
      mergeObject(
        context,
        new js.Object {
          val state: js.UndefOr[ActorState[? <: MythrasActorDataModel]] = actor.getState
        },
      ),
      "Sheet Data",
    )
  }

object MythrasBaseSheet {
  @JSExportStatic
  def name: String = "MythrasActorSheet"

  @JSExportStatic
  def defaultOptions: ActorSheetOptions = {
    val newOptions: ActorSheetOptions = new ActorSheetOptions {
      override val classes: js.Array[String] = List("mythras", "actor", "sheet").toJSArray
      override val width                     = 1024
      override val height                    = 768
    }
    mergeObject(ActorSheet.defaultOptions, newOptions)
  }
}
