package foundry.client

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal
class ActorSheet[A <: Actor[? <: js.Object]](obj: A, options: js.Dictionary[js.Any] = js.Dictionary.empty) extends js.Object {
  def template: String     = js.native
  def getData(options: js.Object = js.Object.apply()): js.Object = js.native
}

@js.native
@JSGlobal
object ActorSheet extends js.Object {
  def defaultOptions: ActorSheetOptions = js.native
}
