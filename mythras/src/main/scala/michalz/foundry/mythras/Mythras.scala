package michalz.foundry.mythras

import michalz.foundry.mythras.init.HooksRegister

object Mythras extends App {
  HooksRegister.registerAll()
}
