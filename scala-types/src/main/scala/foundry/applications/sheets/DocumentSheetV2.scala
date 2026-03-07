package foundry.applications.sheets

import scala.annotation.nowarn

import foundry.applications.api.ApplicationConfiguration
import foundry.applications.api.ApplicationV2
import foundry.client.document.Document

import scalajs.js
import scalajs.js.annotation.JSGlobal

@js.native
trait DocumentSheetOptionsV2[DocumentData <: js.Object, DocumentType <: Document[DocumentData]]
    extends ApplicationConfiguration {
  def document: DocumentType
}

@js.native
@JSGlobal("foundry.applications.sheets.DocumentSheetV2")
class DocumentSheetV2[DocumentData <: js.Object, DocumentType <: Document[DocumentData]](
    options: DocumentSheetOptionsV2[DocumentData, DocumentType],
    @nowarn args: js.Any*
) extends ApplicationV2(options)
