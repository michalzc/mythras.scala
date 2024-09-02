package michalz.foundry.mythras

import foundry.client.GlobalFunctions.loadTemplates
import foundry.client.{Actor, ActorSheet, ActorSheetParams, Actors, Hooks}
import michalz.foundry.client.Config
import michalz.foundry.mythras.application.MythrasCharacterSheet
import michalz.foundry.mythras.document.actor.MythrasActor
import michalz.foundry.mythras.document.actor.data.MythrasActorData
import michalz.foundry.mythras.utils.log

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

object Mythras extends App {

  def preloadTemplates() = {
    val templatePaths = js.Array(
      "systems/mythras/templates/parts/character-sheet/stats.hbs",
      "systems/mythras/templates/parts/character-sheet/combat.hbs",
      "systems/mythras/templates/parts/character-sheet/equipment.hbs",
      "systems/mythras/templates/parts/character-sheet/magic.hbs",
      "systems/mythras/templates/parts/character-sheet/notes.hbs",
      "systems/mythras/templates/parts/character-sheet/attribute-with-skills.hbs",
    )

    loadTemplates(templatePaths)
  }

  @JSExport
  val init: js.Function0[Unit] = { () =>
    log("Initializing!")
    preloadTemplates()

    Config.Actor.documentClass = js.constructorOf[MythrasActor[? <: MythrasActorData]] // DominionActor.createActor(_, _)

    Actors.unregisterSheet("core", js.constructorOf[ActorSheet[Actor[js.Object]]])
    Actors.registerSheet(
      "mythras",
      js.constructorOf[MythrasCharacterSheet],
      new ActorSheetParams(label = "mythras.sheets.character", types = js.Array("character"), makeDefault = true), //
    )
    log("System initialized!")
  }

  Hooks.once("init", init)
}
