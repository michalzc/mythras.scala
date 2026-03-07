package michalz.foundry.mythras

import michalz.foundry.mythras.init.HooksRegister

object Mythras {
  def main(args: Array[String]): Unit = 
    HooksRegister.registerAll()
}
