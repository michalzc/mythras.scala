package michalz.foundry.mythras.application

import foundry.client.FoundryUtils.mergeObject
import foundry.client.apps.data.ApplicationOptions
import foundry.client.apps.{ActorSheet, ActorSheetOptions}
import michalz.foundry.mythras.document.actor.MythrasActor

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import scala.scalajs.js.annotation.JSExportStatic

class MythrasBaseSheet[AD <: MythrasActor.SystemData](actor: MythrasActor[AD], options: ApplicationOptions)
    extends ActorSheet[AD, MythrasActor[AD]](actor, options)

object MythrasBaseSheet {
  @JSExportStatic
  def name: String = "MythrasActorSheet"

  @JSExportStatic
  def defaultOptions: ActorSheetOptions = {
    val newOptions: ActorSheetOptions = new ActorSheetOptions {
      override val classes: js.Array[String] = List("mythras", "actor", "sheet").toJSArray
      override val width   = 1024
      override val height  = 768
    }
    mergeObject(ActorSheet.defaultOptions, newOptions)
  }
}
