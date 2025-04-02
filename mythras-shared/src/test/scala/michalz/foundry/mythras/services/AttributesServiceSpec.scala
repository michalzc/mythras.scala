package michalz.foundry.mythras.services

import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class AttributesServiceSpec extends AnyFlatSpecLike with Matchers with TableDrivenPropertyChecks:

  behavior of "AttributesService"

  it should "calculate max action points" in {

    val actionPointsTab = Table(
      ("Int+Dex", "AP"),
      (-10, 1),
      (0, 1),
      (1, 1),
      (12, 1),
      (13, 2),
      (24, 2),
      (25, 3),
      (36, 3),
      (37, 4),
    )

    forAll(actionPointsTab)((value, expected) => AttributesService.calculateActionPoints(value) shouldEqual expected)
  }

  it should "calculate expierience modifier" in {
    val tab = Table(
      ("CHA", "ExpMod"),
      (-5, -1),
      (0, -1),
      (1, -1),
      (6, -1),
      (7, 0),
      (12, 0),
      (13, 1),
      (18, 1),
      (19, 2),
    )

    forAll(tab)((value, expected) => AttributesService.calculateExperienceMod(value) shouldEqual expected)
  }

  it should "calculate healing rate" in {
    val tab = Table(
      ("CON", "HR"),
      (-1, 1),
      (0, 1),
      (1, 1),
      (6, 1),
      (7, 2),
      (12, 2),
      (13, 3),
      (18, 3),
      (19, 4),
    )

    forAll(tab)((value, expected) => AttributesService.calculateHealingRate(value) shouldEqual expected)
  }

  it should "calculate hit points for Humanoids" in {
    val tab = Table(
      ("CON+SIZ", "Location", "HP"),
      (-1, HumanoidBodyLocation.Leg, 1),
      (0, HumanoidBodyLocation.Leg, 1),
      (1, HumanoidBodyLocation.Leg, 1),
      (5, HumanoidBodyLocation.Leg, 1),
      (6, HumanoidBodyLocation.Leg, 2),
      (10, HumanoidBodyLocation.Leg, 2),
      (45, HumanoidBodyLocation.Leg, 9),
      (46, HumanoidBodyLocation.Leg, 10),
      (-1, HumanoidBodyLocation.Abdomen, 2),
      (0, HumanoidBodyLocation.Abdomen, 2),
      (1, HumanoidBodyLocation.Abdomen, 2),
      (5, HumanoidBodyLocation.Abdomen, 2),
      (6, HumanoidBodyLocation.Abdomen, 3),
      (10, HumanoidBodyLocation.Abdomen, 3),
      (45, HumanoidBodyLocation.Abdomen, 10),
      (46, HumanoidBodyLocation.Abdomen, 11),
      (-1, HumanoidBodyLocation.Chest, 3),
      (5, HumanoidBodyLocation.Chest, 3),
      (6, HumanoidBodyLocation.Chest, 4),
      (45, HumanoidBodyLocation.Chest, 11),
      (-1, HumanoidBodyLocation.Arm, 1),
      (0, HumanoidBodyLocation.Arm, 1),
      (1, HumanoidBodyLocation.Arm, 1),
      (2, HumanoidBodyLocation.Arm, 1),
      (6, HumanoidBodyLocation.Arm, 1),
      (10, HumanoidBodyLocation.Arm, 1),
      (11, HumanoidBodyLocation.Arm, 2),
      (16, HumanoidBodyLocation.Arm, 3),
      (-1, HumanoidBodyLocation.Head, 1),
      (0, HumanoidBodyLocation.Head, 1),
      (1, HumanoidBodyLocation.Head, 1),
      (5, HumanoidBodyLocation.Head, 1),
      (6, HumanoidBodyLocation.Head, 2),
      (10, HumanoidBodyLocation.Head, 2),
      (45, HumanoidBodyLocation.Head, 9),
      (46, HumanoidBodyLocation.Head, 10),
    )

    forAll(tab)((conSize, location, exptected) => location.calculate(conSize) shouldEqual exptected)
  }

  it should "calculate luck points" in {
    val tab = Table(
      ("POW", "LPS"),
      (-1, 1),
      (0, 1),
      (1, 1),
      (6, 1),
      (18, 3),
      (19, 4),
    )

    forAll(tab)((value, expected) => AttributesService.calculateLuckPoints(value) shouldEqual expected)
  }

  it should "calculate magic points" in {
    val tab = Table(
      ("Pow", "MP"),
      (-1, 1),
      (0, 1),
      (1, 1),
      (10, 10),
    )

    forAll(tab)((value, expected) => AttributesService.calculateMagicPoints(value) shouldEqual expected)
  }
