package michalz.foundry.mythras.document.actor.state

import michalz.foundry.mythras.document.actor.data.Attributes
import michalz.foundry.mythras.document.actor.state.FieldWithLabel.fieldToInt
import michalz.foundry.mythras.services.AttributesService

import scala.language.implicitConversions

import scala.scalajs.js

class StateAttributes(attributes: Attributes, characteristics: StateCharacteristics) extends js.Object:
  import characteristics.*
  lazy val actionPoints: Counter = Counter(
    attributes.actionPoints,
    AttributesService.calculateActionPoints(intelligence, dexterity),
  )
  lazy val damageModifier: DamageModifier = DamageModifier(attributes.damageModifier, strength, size)
  lazy val experienceModifier: Attribute  = Attribute(attributes.experienceModifier, AttributesService.calculateExperienceMod(charisma))
  lazy val healingRate: Attribute          = Attribute(attributes.healingRate, AttributesService.calculateHealingRate(constitution))
  lazy val initiativeBonus: Attribute = Attribute(attributes.initiative, AttributesService.calculateInitiativeMod(intelligence, dexterity))
  lazy val luckPoints: Counter = Counter(attributes.luckPoints, AttributesService.calculateLuckPoints(power))
  lazy val magicPoints: Counter = Counter(attributes.magicPoints, AttributesService.calculateMagicPoints(power)) //FIXME: update with devoted
  lazy val movementRate: Field = Field(attributes.movementRate)
