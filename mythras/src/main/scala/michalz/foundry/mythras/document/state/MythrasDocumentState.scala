package michalz.foundry.mythras.document.state

import scala.scalajs.js

import foundry.client.document.Document

class MythrasDocumentState[DataModel <: js.Object, Doc <: Document[DataModel]](
    val document: Doc
) extends js.Object
