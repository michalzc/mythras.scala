package michalz.foundry.client

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("./addons.js", "MythrasAddons")
object MythrasAddons extends js.Any{

  def registerDataModels(): Unit = js.native
}
