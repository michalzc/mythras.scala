

export class CharacterDataModel extends foundry.abstract.DataModel {

  static defineSchema() {
    const fields = foundry.data.fields;

    return {
      characteristics: new fields.SchemaField({
        str: new fields.NumberField({integer: true, min: 0, initial: 0}),
        con: new fields.NumberField({integer: true, min: 0, initial: 0}),
        siz: new fields.NumberField({integer: true, min: 0, initial: 0}),
        dex: new fields.NumberField({integer: true, min: 0, initial: 0}),
        int: new fields.NumberField({integer: true, min: 0, initial: 0}),
        pow: new fields.NumberField({integer: true, min: 0, initial: 0}),
        cha: new fields.NumberField({integer: true, min: 0, initial: 0}),
      })
    }
  }

}