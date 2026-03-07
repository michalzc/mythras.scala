package foundry.applications.api


import scala.annotation.nowarn

import org.scalajs.dom.HTMLElement

import scalajs.js.annotation.JSGlobal
import scalajs.js

@js.native
trait ApplicationConfiguration extends js.Any

@js.native
trait ApplicationRenderContext extends js.Any

@js.native
trait ApplicationRenderOptions extends js.Object

@js.native
@JSGlobal("foundry.applications.api.ApplicationV2")
class ApplicationV2[O <: ApplicationRenderOptions](@nowarn options: ApplicationConfiguration) extends js.Any {
  def _renderHTML(context: ApplicationRenderContext, options: O): js.Promise[HTMLElement] = js.native
}
