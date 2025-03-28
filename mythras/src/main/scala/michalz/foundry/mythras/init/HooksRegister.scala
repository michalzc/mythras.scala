package michalz.foundry.mythras.init
import foundry.client.Hooks

import scala.scalajs.js

object HooksRegister:

  def registerAll(): Unit =
    runInit()

  private def runInit(): Unit =
    Hooks.once("init", js.Any.fromFunction0(init))
