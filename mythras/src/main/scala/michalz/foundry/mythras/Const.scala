package michalz.foundry.mythras

import scala.scalajs.js

object Const extends js.Object:
  val MODULE_NAME  = "mythras-scala"
  val MODULE_TITLE = "Mythras Scala"

  private val actorLocKey = (key: String) => LocKey(key, s"TYPES.Actor.$key")

  object ActorTypes extends js.Object:
    val character: LocKey = actorLocKey("character")
    val npc: LocKey       = actorLocKey("npc")

  private val itemLocKey = (key: String) => LocKey(key, s"TYPES.Item.$key")

  object ItemTypes extends js.Object:
    val armour: LocKey      = itemLocKey("armour")
    val melee: LocKey       = itemLocKey("melee")
    val ranged: LocKey      = itemLocKey("ranged")
    val equipment: LocKey   = itemLocKey("equipment")
    val skill: LocKey       = itemLocKey("skill")
    val spell: LocKey       = itemLocKey("spell")
    val hitLocation: LocKey = itemLocKey("hitLocation")

  object Sheets extends js.Object:
    object Actor extends js.Object:
      object CharacterSheet extends js.Object:
        private val tabKey = (key: String) => LocKey(key, s"mythras.sheets.tabs.$key")
        val tabs: js.Array[LocKey] = js.Array(
          tabKey("character"),
          tabKey("equipment"),
          tabKey("combat"),
          tabKey("magic"),
          tabKey("notes"),
        )

  object Labels extends js.Object:
    val characteristicPath: String = "mythras.labels.characteristics"

class LocKey(val key: String, val localizationKey: String) extends js.Object

object LocKey:
  def apply(key: String, localizationKey: String) = new LocKey(key, localizationKey)
