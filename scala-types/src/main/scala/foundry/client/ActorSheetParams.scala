package foundry.client

import scala.scalajs.js

class ActorSheetParams(
    val types: js.UndefOr[js.Array[String]] = js.undefined,
    val makeDefault: js.UndefOr[Boolean] = js.undefined,
    val label: js.UndefOr[String] = js.undefined,
) extends js.Object {}