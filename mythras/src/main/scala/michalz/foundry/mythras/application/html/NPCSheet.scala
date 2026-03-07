package michalz.foundry.mythras.application.html

import org.scalajs.dom.HTMLElement
import scalatags.JsDom.all.*

import michalz.foundry.mythras.document.actor.MythrasActor
import michalz.foundry.mythras.document.actor.data.MythrasNPCDataModel

object NPCSheet {
  def render(actor: MythrasActor[MythrasNPCDataModel]): HTMLElement =
    div("It's working!").render
}
