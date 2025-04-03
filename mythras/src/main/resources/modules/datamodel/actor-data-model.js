import {createAttributes, createCharacteristics, createCounterWithMod} from './common-data-model.js'

const fields = foundry.data.fields;

function createCharacterProfile() {
  return new fields.SchemaField({
    biography: new fields.HTMLField(),
    notes: new fields.HTMLField(),
    race: new fields.StringField(),
    homeland: new fields.StringField(),
    career: new fields.StringField(),
    socialClass: new fields.StringField(),
    age: new fields.NumberField({integer: true, min: 0}),
    gender: new fields.StringField(),
    frame: new fields.StringField(),
    height: new fields.NumberField({integer: true, min: 0}),
    weight: new fields.NumberField({integer: true, min: 0})
  }, {required: true})
}

export class CharacterDataModel extends foundry.abstract.DataModel {

  static defineSchema() {
    return {
      characteristics: createCharacteristics(),
      attributes: createAttributes(),
      profile: createCharacterProfile(),
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