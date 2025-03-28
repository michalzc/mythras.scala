package foundry.client.apps.data

import foundry.documents.Document

import scala.scalajs.js

@js.native
trait SheetData[SD <: js.Object, D <: Document[SD]] extends js.Object:
  def cssClass: String = js.native
  def editable: Boolean = js.native
  def document: D = js.native
  def data: SD = js.native
  def limited: Boolean = js.native
  def options: js.Object = js.native
  def owner: Boolean = js.native
  def title: String = js.native
