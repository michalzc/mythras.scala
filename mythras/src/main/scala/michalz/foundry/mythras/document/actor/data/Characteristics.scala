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
