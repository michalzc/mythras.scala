package michalz.foundry.mythras.utils

object DamageModifierService:
  private val smallerDice = List("1d2", "1d4", "1d6", "1d8")

  def valueToStep(value: Int): Int = value match
    case v if v <= 5  => 0
    case v if v <= 50 => (v - 1) / 5
    case v            => (v - 1) / 10 + 5

  def modByValue(value: Int): String = modByStep(valueToStep(value))

  def modByStep(step: Int): String = if step < steps.length then steps(step)
  else
    val mul   = (step / 5) - 1
    val idx   = (step % 5) - 1
    val elems = if idx >= 0 then List(s"+${mul}d10", smallerDice(idx)) else List(s"+${mul}d10")
    elems.mkString("+")

  private val steps = Vector(
    "-1d8",     // <= 5
    "-1d6",     // <= 10
    "-1d4",     // <= 15
    "-1d2",     // <= 20
    "+0",       // <= 25
    "+1d2",     // <= 30
    "+1d4",     // <= 35
    "+1d6",     // <= 40
    "+1d8",     // <= 45
    "+1d10",    // <= 50
    "+1d12",    // <= 60
    "+2d6",     // <= 70
    "+1d8+1d6", // <= 80
    "+2d8",     // <= 90
    "+1d10+1d8", // <= 100
  )
