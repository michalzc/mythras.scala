package foundry.client.apps

import scala.scalajs.js

trait ActorSheetOptions extends js.Object {
  def classes: js.UndefOr[js.Array[String]] = js.undefined

  def template: js.UndefOr[String] = js.undefined

  def width: js.UndefOr[Int] = js.undefined

  def height: js.UndefOr[Int] = js.undefined

  def tabs: js.UndefOr[js.Array[TabDef]] = js.undefined
}

trait TabDef extends js.Object {
  val navSelector: String
  val contentSelector: String
  val initial: String
}
