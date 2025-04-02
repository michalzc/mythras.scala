const fields = foundry.data.fields;

export function createFieldWithMod(required = false, initial = 0) {
    const fieldOptions = {integer: true, min: 0, initial: initial, required: required}
    const modOptions = {integer: true, min: 0, initial: 0, required: required}
    const schemaField = new fields.SchemaField({
        base: new fields.NumberField(fieldOptions),
        mod: new fields.NumberField(modOptions)
    }, {required: required});

    Object.defineProperty(schemaField, 'value', {
        get: function() {
            return this.base + this.mod;
        }
    })

    return schemaField
}

export function createCounterWithMod(required = false) {
    const fieldOptions = {integer: true, min: 0, initial: 0, required: true}
    return new fields.SchemaField({
        current: new fields.NumberField(fieldOptions),
        mod: new fields.NumberField(fieldOptions)
    }, {required: required})
}

export function createAttributeWithMod(required = false) {
    return new fields.SchemaField({
        mod: new fields.NumberField({integer: true, min: 0, initial: 0, required: false})
    }, {required: required})
}

export function createCharacteristics() {
    return new fields.SchemaField({
        strength: createFieldWithMod(true),
        constitution: createFieldWithMod(true),
        size: createFieldWithMod(true),
        dexterity: createFieldWithMod(true),
        intelligence: createFieldWithMod(true),
        power: createFieldWithMod(true),
        charisma: createFieldWithMod(true),
    })
}

export function createAttributes() {
    return new fields.SchemaField({
        actionPoints: createCounterWithMod(true),
        damageModifier: createAttributeWithMod(true),
        experienceModifier: createAttributeWithMod(true),
        healingRate: createAttributeWithMod(true),
        initiative: createAttributeWithMod(true),
        luckPoints: createCounterWithMod(true),
        movementRate: createFieldWithMod(true, 6),
        magicPoints: createCounterWithMod(true),
    })
}