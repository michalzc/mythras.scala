package michalz.foundry.mythras.document.actor.data

import scala.scalajs.js

trait Characteristics extends js.Object:
  def strength: FieldWithMod
  def constitution: FieldWithMod
  def size: FieldWithMod
  def dexterity: FieldWithMod
  def intelligence: FieldWithMod
  def power: FieldWithMod
  def charisma: FieldWithMod

object Characteristics:
  def apply(source: Characteristics): Characteristics = new Characteristics:
    override def strength: FieldWithMod     = FieldWithMod(source.strength)
    override def constitution: FieldWithMod = FieldWithMod(source.constitution)
    override def size: FieldWithMod         = FieldWithMod(source.size)
    override def dexterity: FieldWithMod    = FieldWithMod(source.dexterity)
    override def intelligence: FieldWithMod = FieldWithMod(source.intelligence)
    override def power: FieldWithMod        = FieldWithMod(source.power)
    override def charisma: FieldWithMod     = FieldWithMod(source.charisma)
