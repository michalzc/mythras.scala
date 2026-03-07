package michalz.foundry.client

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

import foundry.client.config.BaseConfig

import michalz.foundry.mythras.document.actor.MythrasActor

@js.native
@JSGlobal("CONFIG")
object Config extends BaseConfig:
  val Mythras: js.Dynamic = js.native

object Mythras extends js.Object:
  val Actor: js.Dynamic = js.constructorOf[MythrasActor[? <: js.Object]]
