package foundry.client.apps.data

import foundry.client.document.Actor
import foundry.client.document.ActorData

import scala.scalajs.js

@js.native
trait ActorSheetData[SystemData <: js.Object, A <: Actor[SystemData]] extends SheetData[SystemData, A]:
  def actor: Actor[ActorData[SystemData]] = js.native
