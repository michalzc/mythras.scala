package foundry.client.document

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

trait DocumentData[SystemData <: js.Object] extends js.Object:
  @JSName("type")
  def `type`: String
  def _id: js.UndefOr[String]
  def name: String
  def system: js.UndefOr[SystemData]
  def folder: js.UndefOr[String]
