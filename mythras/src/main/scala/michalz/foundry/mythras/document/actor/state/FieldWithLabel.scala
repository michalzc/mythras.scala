package michalz.foundry.mythras.document.actor.state

import michalz.foundry.mythras.Const
import michalz.foundry.mythras.document.actor.data.FieldWithMod

class FieldWithLabel(val key: String, source: FieldWithMod) extends Field(source):
  lazy val label: String = s"${Const.Labels.characteristicPath}.$key"
  lazy val abbr: String  = key.take(3).toUpperCase()

object FieldWithLabel:
  given fieldToInt: Conversion[FieldWithLabel, Int] with
    def apply(field: FieldWithLabel): Int = field.value