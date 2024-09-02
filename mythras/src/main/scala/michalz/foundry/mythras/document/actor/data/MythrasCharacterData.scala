package michalz.foundry.mythras.document.actor.data

import scala.scalajs.js

@js.native
trait CharacterDescriptors extends js.Object {
  val race: String = js.native
  val hand: String = js.native
  val sex: String  = js.native
  val height: Int  = js.native
  val weight: Int  = js.native
  val hair: String = js.native
  val age: Int     = js.native
}

@js.native
trait CharacterAttributes extends js.Object {
  val vigour: Int    = js.native
  val agility: Int   = js.native
  val stamina: Int   = js.native
  val intuition: Int = js.native
  val intellect: Int = js.native
  val luck: Int      = js.native
}

@js.native
trait Characteristics extends js.Object {
  val injury: Int = js.native
  val aps: Int = js.native
  val favourableRounding: Boolean = js.native
}

@js.native
trait MythrasCharacterData extends MythrasActorData {
  val descriptors: CharacterDescriptors = js.native
  val attributes: CharacterAttributes = js.native
  val characteristics: Characteristics = js.native
}
