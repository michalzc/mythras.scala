package michalz.foundry.mythras.document.actor.data

import michalz.foundry.mythras.document.actor.data.MythrasCharacterData.Cult

import scala.scalajs.js

trait MythrasCharacterData extends MythrasActorSystemData:
  def biography: Option[String]
  def notes: Option[String]
  def cults: List[Cult]

object MythrasCharacterData:
  trait Cult extends js.Object:
    def name: String
    def description: Option[String]
    def rank: String
    def devotionPool: CounterWithMod