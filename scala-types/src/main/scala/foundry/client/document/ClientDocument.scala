package foundry.client.document

import scala.scalajs.js

@js.native
trait ClientDocument extends js.Object:
  def prepareData(): Unit              = js.native
  def prepareDerivedData(): Unit       = js.native
  def prepareEmbeddedDocuments(): Unit = js.native
  def prepareBaseData(): Unit          = js.native
