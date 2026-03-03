package michalz.foundry.mythras.document.item

import scala.scalajs.js

import foundry.client.document.Item
import foundry.client.document.ItemData

class MythrasItem[DataModel <: js.Object](
    data: ItemData[DataModel],
    context: js.Object
) extends Item[DataModel](data, context)
