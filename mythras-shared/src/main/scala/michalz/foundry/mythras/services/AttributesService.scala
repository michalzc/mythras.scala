package michalz.foundry.mythras.services

trait AttributesService:
  protected val ActionPointsBase: AttributeData  = AttributeData(12)
  protected val ExperienceModBase: AttributeData = AttributeData(6, -1, -2)
  protected val HealingRateBase: AttributeData   = AttributeData(6)
  protected val LuckPointsBase: AttributeData    = AttributeData(6)
  protected val MagicPointsBase: AttributeData   = AttributeData(1)

  def calculateActionPoints(int: Int, dex: Int): Int =
    ActionPointsBase.calculate(int + dex)
  def calculateExperienceMod(cha: Int): Int = ExperienceModBase.calculate(cha)
  def calculateHealingRate(con: Int): Int   = HealingRateBase.calculate(con)
  def calculateMaxHitPointsPerLocation(
      con: Int,
      siz: Int,
      location: BodyLocation
  ): Int =
    location.calculate(con + siz)
  def calculateLuckPoints(pow: Int): Int  = LuckPointsBase.calculate(pow)
  def calculateMagicPoints(pow: Int): Int = MagicPointsBase.calculate(pow)
  def calculateInitiativeMod(int: Int, dex: Int): Int =
    scala.math.round((int + dex) / 2.0).toInt

object AttributesService extends AttributesService
