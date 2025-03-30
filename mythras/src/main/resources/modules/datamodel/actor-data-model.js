import {createAttributes, createCharacteristics, createCounterWithMod} from './common-data-model.js'

const fields = foundry.data.fields;


export class CharacterDataModel extends foundry.abstract.DataModel {

  static defineSchema() {
    return {
      characteristics: createCharacteristics(),
      attributes: createAttributes(),
      biography: new fields.HTMLField(),
      notes: new fields.HTMLField(),
      cults: new fields.ArrayField( new fields.SchemaField({
        name: new fields.StringField({required: true}),
        description: new fields.HTMLField({required: false}),
        rank: new fields.StringField({required: true}),
        devotionalPool: createCounterWithMod(false)
      }))
    }
  }
}

export class NpcDataModel extends foundry.abstract.DataModel {
  static defineSchema() {
    return {
      characteristics: createCharacteristics(),
      attributes: createAttributes(),
      description: new fields.HTMLField(),
    }
  }
}