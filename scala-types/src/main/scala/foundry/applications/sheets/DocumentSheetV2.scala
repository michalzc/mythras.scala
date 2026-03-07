package foundry.applications.sheets

import scala.annotation.nowarn
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

import foundry.applications.api.ApplicationConfiguration
import foundry.applications.api.ApplicationRenderContext
import foundry.applications.api.ApplicationRenderOptions
import foundry.applications.api.ApplicationV2
import foundry.client.document.Document

import org.scalajs.dom.HTMLElement

@js.native
trait DocumentSheetOptionsV2[DocumentData <: js.Object, DocumentType <: Document[DocumentData]]
    extends ApplicationConfiguration {
  def document: DocumentType
}

@js.native
trait DocumentSheetRenderOptions extends js.Any

@js.native
@JSGlobal("foundry.applications.sheets.DocumentSheetV2")
class DocumentSheetV2[DocumentData <: js.Object, DocumentType <: Document[DocumentData]](
    options: DocumentSheetOptionsV2[DocumentData, DocumentType],
    @nowarn args: js.Any*
) extends ApplicationV2[ApplicationRenderOptions & DocumentSheetRenderOptions](options) {
  override def _renderHTML(context: ApplicationRenderContext, options:  ApplicationRenderOptions & DocumentSheetRenderOptions): js.Promise[HTMLElement] = js.native
}
