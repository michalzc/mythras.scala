package foundry.client.apps

import scala.scalajs.js

class ActorSheetParams(
    val types: js.UndefOr[js.Array[String]] = js.undefined,
    val makeDefault: js.UndefOr[Boolean] = js.undefined,
    val label: js.UndefOr[String] = js.undefined
) extends js.Object {}

object ActorSheetParams:
  def apply(
      types: js.UndefOr[js.Array[String]] = js.undefined,
      makeDefault: js.UndefOr[Boolean] = js.undefined,
      label: js.UndefOr[String] = js.undefined
  ): ActorSheetParams = new ActorSheetParams(types, makeDefault, label)
