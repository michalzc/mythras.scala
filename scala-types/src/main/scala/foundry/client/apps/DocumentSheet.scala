package foundry.client.apps

import scala.scalajs.js

import foundry.client.apps.data.SheetData
import foundry.client.document.Document

@js.native
trait DocumentSheet[SystemData <: js.Object, D <: Document[
  SystemData
], SD <: SheetData[SystemData, D]]
    extends js.Object:
  def getData(options: js.Object): js.Promise[SD] | SD
  def _getHeaderButtons(): js.Array[js.Object] = js.native
