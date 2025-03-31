package michalz.foundry.mythras.init

import foundry.client.Actors
import foundry.client.GlobalFunctions.loadTemplates
import foundry.client.apps.{ActorSheet, ActorSheetParams}
import michalz.foundry.client.Config
import michalz.foundry.client.MythrasAddons.registerDataModels
import michalz.foundry.mythras.application.{MythrasCharacterSheet, MythrasNPCSheet}
import michalz.foundry.mythras.document.actor.MythrasActor
import michalz.foundry.mythras.document.actor.data.MythrasActorDataModel
import michalz.foundry.mythras.document.item.MythrasItem
import michalz.foundry.mythras.utils.log

import scala.scalajs.js

def preloadTemplates() = {
  val templatePaths = js.Array(
    "systems/mythras-scala/templates/parts/character-sheet/stats.hbs",
  )

  loadTemplates(templatePaths)
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
