package michalz.foundry.mythras.document.item

import foundry.client.document.{Item, ItemData}

import scala.scalajs.js

class MythrasItem[DataModel <: js.Object](data: ItemData[DataModel], context: js.Object) extends Item[DataModel](data, context)
