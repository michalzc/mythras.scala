package michalz.foundry.mythras.services

/**
 * Service for calculating damage modifiers based on character attributes.
 * The damage modifier is represented as dice notation (e.g., "+1d6", "-1d4").
 */
trait DamageModifierService:
  /** Smaller dice types used in damage calculations */
  private val SmallerDice = Vector("1d2", "1d4", "1d6", "1d8")

  /** Threshold values for step calculations */
  private val LowValueThreshold = 5
  private val MidValueThreshold = 50
  private val LowStepDivisor = 5
  private val HighStepDivisor = 10
  private val HighStepOffset = 5

  /** Lookup table for mapping steps to damage modifiers */
  private val DamageModifierByStep = Vector(
    "-1d8", // Step 0: <= 5
    "-1d6", // Step 1: <= 10
    "-1d4", // Step 2: <= 15
    "-1d2", // Step 3: <= 20
    "+0", // Step 4: <= 25
    "+1d2", // Step 5: <= 30
    "+1d4", // Step 6: <= 35
    "+1d6", // Step 7: <= 40
    "+1d8", // Step 8: <= 45
    "+1d10", // Step 9: <= 50
    "+1d12", // Step 10: <= 60
    "+2d6", // Step 11: <= 70
    "+1d8+1d6", // Step 12: <= 80
    "+2d8", // Step 13: <= 90
    "+1d10+1d8", // Step 14: <= 100
  )

  /**
   * Converts a character attribute value to a damage step.
   *
   * @param value Attribute value to convert
   * @return The corresponding damage step
   */
  def valueToStep(value: Int): Int = value match
    case v if v <= LowValueThreshold => 0
    case v if v <= MidValueThreshold => (v - 1) / LowStepDivisor
    case v => (v - 1) / HighStepDivisor + HighStepOffset

  /**
   * Get damage modifier string based on attribute value.
   *
   * @param value Attribute value
   * @return Damage modifier in dice notation
   */
  def modByValue(value: Int): String = modByStep(valueToStep(value))

  /**
   * Get damage modifier string based on damage step.
   *
   * @param step Damage step
   * @return Damage modifier in dice notation
   */
  def modByStep(step: Int): String =
    if step < 0 then "-1d8" // Handle negative steps
    else if step < DamageModifierByStep.length then DamageModifierByStep(step)
    else calculateHighStep(step)

  /**
   * Calculate damage modifier for steps beyond the predefined table.
   *
   * @param step The high damage step to calculate
   * @return Damage modifier in dice notation
   */
  private def calculateHighStep(step: Int): String =
    val multiplier = (step / 5) - 1
    val diceIndex = (step % 5) - 1

    val elements =
      if diceIndex >= 0 then
        List(s"+${multiplier}d10", SmallerDice(diceIndex))
      else
        List(s"+${multiplier}d10")

    elements.mkString("+")

object DamageModifierService extends DamageModifierService