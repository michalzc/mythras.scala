package michalz.foundry.mythras.document.actor.data

import michalz.foundry.mythras.document.actor.data.MythrasCharacterDataModel.Cult

import scala.scalajs.js

trait MythrasCharacterDataModel extends MythrasActorDataModel:
  def biography: Option[String]
  def notes: Option[String]
  def cults: List[Cult]

object MythrasCharacterDataModel:
  trait Cult extends js.Object:
    def name: String
    def description: Option[String]
    def rank: String
    def devotionPool: CounterWithMod
