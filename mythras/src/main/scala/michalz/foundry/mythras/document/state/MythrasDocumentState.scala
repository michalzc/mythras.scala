package michalz.foundry.mythras.document.state

import foundry.client.document.Document

import scala.scalajs.js

class MythrasDocumentState[DataModel <: js.Object, Doc <: Document[DataModel]](
    val document: Doc
) extends js.Object
