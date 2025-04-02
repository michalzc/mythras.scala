package michalz.foundry.mythras.services

trait BaseAttributeData:
  def base: Int
  def minimalValue: Int
  def modifier: Int

  def calculate(value: Int): Int =
    scala.math.max(minimalValue, (value + base - 1) / base + modifier)

case class AttributeData(base: Int, minimalValue: Int = 1, modifier: Int = 0) extends BaseAttributeData