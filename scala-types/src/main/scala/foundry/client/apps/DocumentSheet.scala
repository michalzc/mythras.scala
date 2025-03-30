package foundry.client.apps

import foundry.client.apps.data.SheetData
import foundry.client.document.Document

import scala.scalajs.js

@js.native
trait DocumentSheet[SystemData <: js.Object, D <: Document[SystemData], SD <: SheetData[SystemData, D]] extends js.Object:
  def getData(options: js.Object): js.Promise[SD] | SD
