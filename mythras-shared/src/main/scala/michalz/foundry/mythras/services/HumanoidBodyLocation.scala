package michalz.foundry.mythras.services

trait BodyLocation extends BaseAttributeData

enum HumanoidBodyLocation(val base: Int, val minimalValue: Int = 1, val modifier: Int = 0) extends BodyLocation:
  case Leg     extends HumanoidBodyLocation(5)
  case Abdomen extends HumanoidBodyLocation(5, 2, 1)
  case Chest   extends HumanoidBodyLocation(5, 3, 2)
  case Arm     extends HumanoidBodyLocation(5, 1, -1)
  case Head    extends HumanoidBodyLocation(5)
