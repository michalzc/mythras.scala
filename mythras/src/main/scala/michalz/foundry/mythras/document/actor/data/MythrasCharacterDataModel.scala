package michalz.foundry.mythras.document.actor.data

import michalz.foundry.mythras.document.actor.data.MythrasCharacterDataModel.Cult

import scala.scalajs.js

trait MythrasCharacterDataModel extends MythrasActorDataModel:
  def biography: Option[String]
  def notes: Option[String]
  def cults: List[Cult]

object MythrasCharacterDataModel:

  def apply(source: MythrasCharacterDataModel): MythrasCharacterDataModel = new MythrasCharacterDataModel:
    override def biography: Option[String]        = source.biography
    override def notes: Option[String]            = source.notes
    override def cults: List[Cult]                = source.cults
    override def characteristics: Characteristics = Characteristics(source.characteristics)
    override def attributes: Attributes           = Attributes(source.attributes)

  trait Cult extends js.Object:
    def name: String
    def description: Option[String]
    def rank: String
    def devotionPool: CounterWithMod
