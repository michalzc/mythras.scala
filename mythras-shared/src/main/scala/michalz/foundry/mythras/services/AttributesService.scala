package michalz.foundry.mythras.services

trait AttributesService:
  protected val ActionPointsBase  = AttributeData(12)
  protected val ExperienceModBase = AttributeData(6, -1, -2)
  protected val HealingRateBase   = AttributeData(6)
  protected val LuckPointsBase    = AttributeData(6)
  protected val MagicPointsBase   = AttributeData(1)

  def calculateActionPoints(value: Int): Int = ActionPointsBase.calculate(value)
  def calculateExperienceMod(value: Int): Int     = ExperienceModBase.calculate(value)
  def calculateHealingRate(value: Int): Int       = HealingRateBase.calculate(value)
  def calculateMaxHitPointsPerLocation(value: Int, location: BodyLocation): Int =
    location.calculate(value)
  def calculateLuckPoints(value: Int): Int = LuckPointsBase.calculate(value)
  def calculateMagicPoints(value: Int): Int = MagicPointsBase.calculate(value)

object AttributesService extends AttributesService