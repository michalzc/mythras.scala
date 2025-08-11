package foundry.client.apps.utils

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

class ApplicationHeaderButton(
    val label: String,
    @JSName("class")
    val `class`: String,
    val icon: String,
    val onclick: js.UndefOr[js.Function0[Unit | js.Promise[Unit]]]
) extends js.Object

object ApplicationHeaderButton:
  def apply(
      label: String,
      `class`: String,
      icon: String,
      onclick: js.UndefOr[js.Function0[Unit | js.Promise[Unit]]] = js.undefined
  ): ApplicationHeaderButton =
    new ApplicationHeaderButton(label, `class`, icon, onclick)
