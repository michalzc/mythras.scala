const fields = foundry.data.fields;

export function createFieldWithMod(required = false) {
    const fieldOptions = {integer: true, min: 0, initial: 0, required: true}
    return new fields.SchemaField({
        base: new fields.NumberField(fieldOptions),
        mod: new fields.NumberField(fieldOptions)
    }, {required: required})
}

export function createCounterWithMod(required = false) {
    const fieldOptions = {integer: true, min: 0, initial: 0, required: true}
    return new fields.SchemaField({
        max: new fields.NumberField(fieldOptions),
        current: new fields.NumberField(fieldOptions),
        mod: new fields.NumberField(fieldOptions)
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
        damageModifier: createFieldWithMod(true),
        experienceModifier: createFieldWithMod(true),
        healingRate: createFieldWithMod(true),
        initiative: createFieldWithMod(true),
        luckPoints: createCounterWithMod(true),
        movementRate: createFieldWithMod(true),
        magicPoints: new fields.SchemaField({
            max: new fields.NumberField({integer: true, initial: 0, required: true}),
            current: new fields.NumberField({integer: true, initial: 0, required: true}),
            mod: new fields.NumberField({integer: true, initial: 0, required: true}),
            active: new fields.NumberField({integer: true, min: 0, initial: 0, required: true})
        }, {required: true}),
    })
}