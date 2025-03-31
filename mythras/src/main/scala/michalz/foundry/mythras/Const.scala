package michalz.foundry.mythras

import scala.scalajs.js

object Const extends js.Object:
  val MODULE_NAME  = "mythras-scala"
  val MODULE_TITLE = "Mythras Scala"

  private val actorLocKey = (key: String) => LocKey(key, s"TYPES.Actor.$key")

  object ActorTypes extends js.Object:
    val character: LocKey = actorLocKey("character")
    val npc: LocKey = actorLocKey("npc")

  private val itemLocKey = (key: String) => LocKey(key, s"TYPES.Item.$key")

  object ItemTypes extends js.Object:
    val armour: LocKey      = itemLocKey("armour")
    val melee: LocKey       = itemLocKey("melee")
    val ranged: LocKey      = itemLocKey("ranged")
    val equipment: LocKey   = itemLocKey("equipment")
    val skill: LocKey       = itemLocKey("skill")
    val spell: LocKey       = itemLocKey("spell")
    val hitLocation: LocKey = itemLocKey("hitLocation")


class LocKey(val key: String, val localizationKey: String) extends js.Object

object LocKey:
  def apply(key: String, localizationKey: String) = new LocKey(key, localizationKey)
