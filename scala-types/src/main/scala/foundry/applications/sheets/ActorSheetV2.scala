package foundry.applications.sheets

import foundry.client.document.Actor

import scalajs.js
import scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("foundry.applications.sheets.ActorSheetV2")
class ActorSheetV2[SystemData <: js.Object, A <: Actor[SystemData]](
    options: DocumentSheetOptionsV2[SystemData, A],
    args: js.Any*
) extends DocumentSheetV2[SystemData, A](options, args) {}
