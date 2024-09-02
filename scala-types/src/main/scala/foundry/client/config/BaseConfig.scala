package foundry.client.config

import scala.scalajs.js

@js.native
trait BaseConfig extends js.Object {
  def Actor: ActorConfig = js.native
  def Item: ItemConfig   = js.native
}
