package foundry.applications

import scalajs.js
import scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("foundry.applications.handlebars")
object HandlebarsFunctions extends js.Any {
  def loadTemplates(templates: js.Array[String]): js.Any = js.native
}
