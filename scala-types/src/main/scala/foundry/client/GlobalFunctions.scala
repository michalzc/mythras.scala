package foundry.client

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSName}

@js.native
@JSGlobalScope
object GlobalFunctions extends js.Object {
  def mergeObject[L <: js.Object, R <: js.Object](original: L, other: R): L = js.native

  def loadTemplates(templates: js.Array[String]): js.Any = js.native

  val game: Game = js.native
}
