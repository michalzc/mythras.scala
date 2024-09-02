package foundry.client

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("foundry.utils")
object FoundryUtils extends js.Object:
  def mergeObject[L <: js.Object, R <: js.Object](original: L, other: R): L = js.native
