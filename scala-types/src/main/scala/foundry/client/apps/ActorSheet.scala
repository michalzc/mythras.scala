package foundry.client.apps

import foundry.client.apps.data.{ActorSheetData, ApplicationOptions}
import foundry.client.document.Actor

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal
class ActorSheet[SystemData <: js.Object, A <: Actor[SystemData]](actor: A, options: ApplicationOptions)
    extends DocumentSheet[SystemData, A, ActorSheetData[SystemData, A]] {
  type DataType = js.Promise[ActorSheetData[SystemData, A]] | ActorSheetData[SystemData, A]
  def template: String                      = js.native
  def getData(options: js.Object): DataType = js.native
}

@js.native
@JSGlobal
object ActorSheet extends js.Object {
  def defaultOptions: ActorSheetOptions = js.native
}
