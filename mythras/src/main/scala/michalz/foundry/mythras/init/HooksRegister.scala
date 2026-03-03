package michalz.foundry.mythras.init
import scala.scalajs.js

import foundry.client.Hooks

object HooksRegister:

  def registerAll(): Unit =
    registerInit()

  private def registerInit(): Unit =
    Hooks.once("init", js.Any.fromFunction0(init))
