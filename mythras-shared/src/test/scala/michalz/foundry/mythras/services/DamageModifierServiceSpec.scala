package michalz.foundry.mythras.services

import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class DamageModifierServiceSpec extends AnyFlatSpecLike with Matchers with TableDrivenPropertyChecks {

  val dmData = Table(
    ("step", "value", "dmmod"),
    (0, -10, "-1d8"),
    (0, -5, "-1d8"),
    (0, 0, "-1d8"),
    (0, 3, "-1d8"),
    (0, 5, "-1d8"),
    (1, 6, "-1d6"),
    (1, 10, "-1d6"),
    (2, 11, "-1d4"),
    (2, 15, "-1d4"),
    (3, 16, "-1d2"),
    (3, 20, "-1d2"),
    (4, 21, "+0"),
    (4, 25, "+0"),
    (5, 26, "+1d2"),
    (5, 30, "+1d2"),
    (6, 31, "+1d4"),
    (6, 35, "+1d4"),
    (9, 50, "+1d10"),
    (10, 60, "+1d12"),
    (11, 61, "+2d6"),
    (11, 69, "+2d6"),
    (11, 70, "+2d6"),
    (12, 71, "+1d8+1d6"),
    (16, 120, "+2d10+1d2"),
    (17, 121, "+2d10+1d4"),
    (18, 131, "+2d10+1d6"),
    (18, 140, "+2d10+1d6"),
    (19, 141, "+2d10+1d8"),
    (19, 150, "+2d10+1d8"),
    (20, 151, "+3d10"),
    (25, 201, "+4d10"),
    (25, 210, "+4d10"),
    (26, 211, "+4d10+1d2"),
    (29, 241, "+4d10+1d8")
  )

  it should s"calculate damage modifier" in {

    forAll(dmData) { (expectedStep, value, expectedMod) =>
      val step = DamageModifierService.valueToStep(value)
      val mod  = DamageModifierService.modByValue(value)
      (step, mod) shouldEqual (expectedStep, expectedMod)
    }
  }

}
