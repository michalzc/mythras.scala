package michalz.foundry.mythras.document.actor.data

import michalz.foundry.mythras.document.actor.data.MythrasCharacterDataModel.{Cult, Profile}

import scala.scalajs.js

trait MythrasCharacterDataModel extends MythrasActorDataModel:
  def cults: List[Cult]
  def profile: Profile

object MythrasCharacterDataModel:

  trait Cult extends js.Object:
    def name: String
    def description: Option[String]
    def rank: String
    def devotionPool: CounterWithMod

  trait Profile extends js.Object:
    def biography: Option[String]
    def notes: Option[String]
    def race: Option[String]
    def homeland: Option[String]
    def career: Option[String]
    def socialClass: Option[String]
    def age: Option[Int]
    def gender: Option[String]
    def frame: Option[String]
    def height: Option[String]
    def weight: Option[String]
