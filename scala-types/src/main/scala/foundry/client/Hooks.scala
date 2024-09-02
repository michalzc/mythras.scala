package foundry.client

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal
object Hooks extends js.Any {

  def once[R](hook: String, function: js.Function0[R]): Int              = js.native
  def once[T, R](hook: String, function: js.Function1[T, R]): Int        = js.native
  def once[T1, T2, R](hook: String, function: Function2[T1, T2, R]): Int = js.native
}
