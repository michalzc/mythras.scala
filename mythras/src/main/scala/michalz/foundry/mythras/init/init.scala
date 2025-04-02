package michalz.foundry.mythras.init

import foundry.client.Actors
import foundry.client.GlobalFunctions.loadTemplates
import foundry.client.apps.{ActorSheet, ActorSheetParams}
import michalz.foundry.client.Config
import michalz.foundry.client.MythrasAddons.registerDataModels
import michalz.foundry.mythras.Const
import michalz.foundry.mythras.application.{MythrasCharacterSheet, MythrasNPCSheet}
import michalz.foundry.mythras.document.actor.MythrasActor
import michalz.foundry.mythras.document.actor.data.MythrasActorDataModel
import michalz.foundry.mythras.document.item.MythrasItem
import michalz.foundry.mythras.utils.{log, logObject}

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

def preloadTemplates() = {
  val templatesBasePath = s"systems/${Const.MODULE_NAME}/templates/parts"
  val templates = List(
    "sheet/actor/character-sheet-character.hbs",
  )

  loadTemplates(logObject(templates.map(path => s"$templatesBasePath/$path").toJSArray, "Preloading templates"))
}

def init(): Unit =
  log("Initializing new way!")
  preloadTemplates()
  registerDataModels()

  Config.Actor.documentClass = js.constructorOf[MythrasActor[? <: MythrasActorDataModel]]
  Config.Item.documentClass = js.constructorOf[MythrasItem[? <: js.Object]]

  Actors.unregisterSheet("core", js.constructorOf[ActorSheet[?, ?]])
  Actors.registerSheet(
    "mythras",
    js.constructorOf[MythrasCharacterSheet],
    ActorSheetParams(label = "mythras.sheets.character", types = js.Array("character"), makeDefault = true), //
  )
  Actors.registerSheet(
    "mythras",
    js.constructorOf[MythrasNPCSheet],
    ActorSheetParams(label = "mythras.sheets.npc", types = js.Array("npc"), makeDefault = true),
  )
  log("System initialized!")
