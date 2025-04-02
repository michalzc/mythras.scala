package michalz.foundry.mythras.document.actor.state

import foundry.client.document.Item
import michalz.foundry.mythras.document.actor.MythrasActor
import michalz.foundry.mythras.document.actor.data.MythrasActorDataModel
import michalz.foundry.mythras.document.item.MythrasItem
import michalz.foundry.mythras.document.state.MythrasDocumentState

import scala.scalajs.js
import scala.scalajs.js.Dictionary
import scala.scalajs.js.JSConverters.*

class ActorState[DataModel <: MythrasActorDataModel](val actor: MythrasActor[DataModel]) extends MythrasDocumentState(actor):

  def getActor(): MythrasActor[DataModel] = actor

  lazy val items: js.Array[Item[?]]                        = js.Array.from(actor.items).map(_.asInstanceOf[MythrasItem[?]])
  private lazy val itemMap: Map[String, js.Array[Item[?]]] = items.groupBy(_.`type`)
  lazy val itemsDict: Dictionary[js.Array[Item[?]]]        = itemMap.toJSDictionary
  lazy val attributes: StateAttributes                     = StateAttributes(actor.system.attributes)
  lazy val characteristics: StateCharacteristics           = StateCharacteristics(actor.system.characteristics)
