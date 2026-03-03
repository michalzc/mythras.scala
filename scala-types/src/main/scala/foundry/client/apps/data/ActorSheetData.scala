package foundry.client.apps.data

import scala.scalajs.js

import foundry.client.document.Actor
import foundry.client.document.ActorData

@js.native
trait ActorSheetData[SystemData <: js.Object, A <: Actor[SystemData]] extends SheetData[SystemData, A]:
  def actor: Actor[ActorData[SystemData]] = js.native
