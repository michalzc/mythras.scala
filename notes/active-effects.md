
## Me
How to properly design data model with using effects in future in mind? I suppose having only one value i.e. `system.characteristic.str = 10` may be problematic later, when active effect will be defined and data will be edited.

## Me
Could you give me those examples in JS? I will handle translating it to scala.js. I need to understand foundryvtt internals in this case

## AI Assistant
# FoundryVTT Data Model and Active Effects in JavaScript

I'll provide JavaScript examples showing how to design a data model in FoundryVTT that properly handles active effects. These examples will help you understand the FoundryVTT internals better, which you can then translate to Scala.js.

## 1. Basic Data Structure in FoundryVTT

First, let's define how your system's template.json would structure the data model:

```json
{
  "Actor": {
    "types": ["character"],
    "templates": {
      "characteristics": {
        "characteristics": {
          "str": {
            "base": 10,
            "value": 10,
            "mod": 0
          },
          "dex": {
            "base": 10,
            "value": 10,
            "mod": 0
          }
          // other characteristics...
        }
      }
    },
    "character": {
      "templates": ["characteristics"]
      // other character-specific data...
    }
  }
}
```

## 2. Actor Data Preparation

Here's how you would implement the data preparation to handle active effects:

```javascript
// In your actor class extension
class MyActorClass extends Actor {
  /** @override */
  prepareData() {
    // Always call the super first
    super.prepareData();
    
    // Prepare base characteristics
    this._prepareCharacteristicsData();
    
    // Apply active effects
    if (this.type === "character") {
      this._applyActiveEffects();
    }
    
    // Calculate derived data (after effects are applied)
    this._calculateDerivedData();
  }
  
  _prepareCharacteristicsData() {
    const data = this.system;
    // Ensure base values are set
    for (let [key, char] of Object.entries(data.characteristics)) {
      // Make sure base values are sensible
      char.base = Number.isNumeric(char.base) ? parseInt(char.base) : 10;
      // Set initial value equal to base before effects
      char.value = char.base;
    }
  }
  
  _applyActiveEffects() {
    // ActiveEffect management is mostly handled by FoundryVTT core
    // This is to handle special cases or derived calculations
  }
  
  _calculateDerivedData() {
    const data = this.system;
    // Calculate derived statistics based on current values (after effects)
    for (let [key, char] of Object.entries(data.characteristics)) {
      // Calculate ability modifier (D&D style)
      char.mod = Math.floor((char.value - 10) / 2);
    }
  }
}
```

## 3. Registering Active Effect Change Keys

FoundryVTT needs to know which data fields can be affected by Active Effects. You register these in your system's init hook:

```javascript
Hooks.once("init", function() {
  // Register system settings
  
  // Register effect change keys
  CONFIG.ActiveEffect.legacyTransferral = false; // Use modern effect transfer
  
  // Define custom effect modes if needed
  CONFIG.ActiveEffect.MULTIPLY_BASE_KEEP_SCALING = "MULTIPLY_BASE_KEEP_SCALING";
  
  // Register effect paths
  ActiveEffect.SYSTEM_EFFECTS_PATHS = {
    "system.characteristics.str.value": { type: "number" },
    "system.characteristics.dex.value": { type: "number" },
    // Add other paths here
  };
});
```

## 4. Custom Active Effect Handlers

Sometimes, you need to handle active effects in a specific way:

```javascript
// In your system's module file
Hooks.on("applyActiveEffect", (actor, effect, change) => {
  // This hook is called for each active effect change
  
  // You can implement custom handling for specific paths
  if (change.key.startsWith("system.characteristics")) {
    // Extract the characteristic name
    const parts = change.key.split(".");
    const characteristic = parts[2];
    
    // Handle different modes
    switch (change.mode) {
      case CONST.ACTIVE_EFFECT_MODES.ADD:
        // Simple addition - handled automatically by foundry
        return true; // Let foundry apply this normally
      
      case CONST.ACTIVE_EFFECT_MODES.MULTIPLY:
        // Custom multiplication logic
        const baseValue = actor.system.characteristics[characteristic].base;
        const multiplier = parseFloat(change.value);
        actor.system.characteristics[characteristic].value = Math.round(baseValue * multiplier);
        return false; // We handled it, don't let foundry apply it
        
      case CONST.ACTIVE_EFFECT_MODES.OVERRIDE:
        // Override completely - handled automatically
        return true;
        
      case CONFIG.ActiveEffect.MULTIPLY_BASE_KEEP_SCALING:
        // Custom mode example
        const currentValue = actor.system.characteristics[characteristic].value;
        const customMultiplier = parseFloat(change.value);
        actor.system.characteristics[characteristic].value = Math.round(currentValue * customMultiplier);
        return false;
    }
  }
  
  // For other changes, let foundry handle them
  return true;
});
```

## 5. Creating a Custom Effect

```javascript
// Creating a new effect (e.g., from an item)
function createStrengthBoostEffect(source, value) {
  return {
    label: "Strength Boost",
    icon: "icons/magic/buff.svg",
    origin: source.uuid, // The UUID of the source item
    duration: {
      rounds: 10
    },
    changes: [{
      key: "system.characteristics.str.value",
      mode: CONST.ACTIVE_EFFECT_MODES.ADD,
      value: value.toString()
    }],
    flags: {
      mySystem: {
        effectType: "buff"
      }
    }
  };
}

// When a spell or item is used:
async function castBullsStrength(actor, item) {
  const effect = createStrengthBoostEffect(item, 4);
  await actor.createEmbeddedDocuments("ActiveEffect", [effect]);
}
```

## 6. Custom Active Effect Sheet for more Options

```javascript
class MyEffectSheet extends ActiveEffectConfig {
  /** @override */
  static get defaultOptions() {
    return mergeObject(super.defaultOptions, {
      classes: ["sheet", "active-effect-sheet"],
      template: "systems/mysystem/templates/effects/effect-sheet.hbs",
      width: 580,
      height: 'auto'
    });
  }
  
  /** @override */
  getData() {
    const data = super.getData();
    
    // Add custom modes for the dropdown
    data.modes = Object.entries(CONST.ACTIVE_EFFECT_MODES).reduce((obj, e) => {
      obj[e[1]] = game.i18n.localize(`EFFECT.MODE_${e[0]}`);
      return obj;
    }, {});
    
    // Add custom mode
    data.modes[CONFIG.ActiveEffect.MULTIPLY_BASE_KEEP_SCALING] = "Multiply (Keep Scaling)";
    
    return data;
  }
}

// Register the custom sheet
Hooks.once("init", function() {
  DocumentSheetConfig.registerSheet(ActiveEffect, "mysystem", MyEffectSheet, {makeDefault: true});
});
```

## 7. More Complex Effect Handling with Priorities and Stacking

Here's how you can implement a more sophisticated active effect system that handles priorities and stacking rules:

```javascript
// In your Actor class
_prepareActiveEffectFields() {
  // Get all effects
  const effects = this.effects.filter(e => !e.disabled);
  
  // Group effects by the field they modify
  const effectsByField = {};
  for (let effect of effects) {
    for (let change of effect.changes) {
      if (!effectsByField[change.key]) effectsByField[change.key] = [];
      effectsByField[change.key].push({
        effect: effect,
        change: change,
        priority: effect.flags.priority || 0
      });
    }
  }
  
  // Process each field according to stacking rules
  for (let [key, changes] of Object.entries(effectsByField)) {
    // Example characteristic key: system.characteristics.str.value
    if (key.match(/system\.characteristics\.[a-z]{3}\.value/)) {
      const characteristicKey = key.split('.')[2]; // e.g., "str"
      const base = this.system.characteristics[characteristicKey].base;
      
      // Sort by priority
      changes.sort((a, b) => a.priority - b.priority);
      
      // Group by mode
      const byMode = {
        add: changes.filter(c => c.change.mode === CONST.ACTIVE_EFFECT_MODES.ADD),
        multiply: changes.filter(c => c.change.mode === CONST.ACTIVE_EFFECT_MODES.MULTIPLY),
        override: changes.filter(c => c.change.mode === CONST.ACTIVE_EFFECT_MODES.OVERRIDE),
      };
      
      // Calculate final value
      let value = base;
      
      // Apply all additive modifiers
      const addTotal = byMode.add.reduce((sum, c) => sum + Number(c.change.value), 0);
      value += addTotal;
      
      // Apply multiplicative modifiers
      const multTotal = byMode.multiply.reduce((prod, c) => prod * Number(c.change.value), 1);
      value = Math.round(value * multTotal);
      
      // Finally check for overrides (highest priority wins)
      if (byMode.override.length) {
        // Sort by priority, highest last
        byMode.override.sort((a, b) => a.priority - b.priority);
        value = Number(byMode.override[byMode.override.length - 1].change.value);
      }
      
      // Update the value
      this.system.characteristics[characteristicKey].value = value;
    }
  }
}
```

## 8. Effect Conditions (for conditional modifiers)

FoundryVTT v10+ supports conditional active effects. Here's how to work with them:

```javascript
// Define a custom effect that only applies against certain targets
const effect = {
  label: "Undead Hunter",
  icon: "icons/weapons/swords/sword-holy.svg",
  changes: [{
    key: "system.bonuses.weaponAttack",
    mode: CONST.ACTIVE_EFFECT_MODES.ADD,
    value: "2",
    priority: 20
  }],
  flags: {
    mySystem: {
      effectType: "feat"
    },
    core: {
      statusId: "undeadHunter"
    }
  },
  // This makes the effect only apply when the condition is true
  condition: "target?.type === 'undead'"
};

// You can also evaluate conditions manually
function evaluateEffectCondition(effect, context = {}) {
  if (!effect.condition) return true;
  
  try {
    // Creating a function from the condition string
    const fn = new Function("target", "source", "effect", `return ${effect.condition};`);
    return fn(context.target, context.source, effect);
  } catch(err) {
    console.error("Error evaluating effect condition", err);
    return false;
  }
}
```

## 9. Reacting to Effect Changes

```javascript
// Hook into effect changes
Hooks.on("createActiveEffect", (effect) => {
  const actor = effect.parent;
  if (!actor) return;
  
  // Update UI or take other actions
  ui.notifications.info(`${effect.label} applied to ${actor.name}`);
  
  // Trigger a re-render
  actor.sheet.render(false);
});

Hooks.on("deleteActiveEffect", (effect) => {
  const actor = effect.parent;
  if (!actor) return;
  
  // Update UI or take other actions
  ui.notifications.info(`${effect.label} removed from ${actor.name}`);
  
  // Trigger a re-render
  actor.sheet.render(false);
});
```

## 10. Real-World Example: Character Sheet Integration

Here's how you'd integrate this with a character sheet:

```javascript
class MyActorSheet extends ActorSheet {
  /** @override */
  static get defaultOptions() {
    return mergeObject(super.defaultOptions, {
      classes: ["mysystem", "sheet", "actor"],
      template: "systems/mysystem/templates/actor/actor-sheet.hbs",
      width: 600,
      height: 680,
      tabs: [{ navSelector: ".sheet-tabs", contentSelector: ".sheet-body", initial: "stats" }]
    });
  }
  
  /** @override */
  getData() {
    const data = super.getData();
    
    // Add effect information for display
    // Group effects by characteristic
    data.effectsByCharacteristic = {};
    for (let characteristic of Object.keys(data.actor.system.characteristics)) {
      data.effectsByCharacteristic[characteristic] = this._getEffectsForCharacteristic(characteristic);
    }
    
    return data;
  }
  
  _getEffectsForCharacteristic(characteristic) {
    return this.actor.effects.filter(e => {
      return e.changes.some(c => c.key === `system.characteristics.${characteristic}.value`);
    }).map(e => {
      // Get the relevant change for this characteristic
      const change = e.changes.find(c => c.key === `system.characteristics.${characteristic}.value`);
      return {
        name: e.label,
        value: change.value,
        mode: change.mode,
        isAdd: change.mode === CONST.ACTIVE_EFFECT_MODES.ADD,
        isMultiply: change.mode === CONST.ACTIVE_EFFECT_MODES.MULTIPLY,
        isOverride: change.mode === CONST.ACTIVE_EFFECT_MODES.OVERRIDE,
        icon: e.icon,
        id: e.id
      };
    });
  }
  
  /** @override */
  activateListeners(html) {
    super.activateListeners(html);
    
    // Add listeners for effect-related buttons
    html.find('.effect-control').click(this._onEffectControl.bind(this));
  }
  
  _onEffectControl(event) {
    event.preventDefault();
    const button = event.currentTarget;
    const li = button.closest("li");
    const effectId = li.dataset.effectId;
    
    // Handle different operations
    switch (button.dataset.action) {
      case "edit":
        const effect = this.actor.effects.get(effectId);
        effect.sheet.render(true);
        break;
      case "delete":
        this.actor.deleteEmbeddedDocuments("ActiveEffect", [effectId]);
        break;
      case "toggle":
        const effect2 = this.actor.effects.get(effectId);
        effect2.update({disabled: !effect2.disabled});
        break;
    }
  }
}
```

## 11. Handlebars Template for Character Sheet

```textmate
<div class="characteristic">
  <label>{{localize "CHAR.STR"}}</label>
  <div class="characteristic-block">
    <div class="value-block">
      <!-- Base value input -->
      <input type="number" name="system.characteristics.str.base" value="{{actor.system.characteristics.str.base}}">
      <!-- Final value display (after effects) -->
      <div class="final-value">{{actor.system.characteristics.str.value}}</div>
      <!-- Modifier display -->
      <div class="modifier">{{numberFormat actor.system.characteristics.str.mod sign=true}}</div>
    </div>
    
    <!-- Effects list -->
    <div class="effects-list">
      {{#each effectsByCharacteristic.str as |effect|}}
      <li class="effect-item" data-effect-id="{{effect.id}}">
        <img src="{{effect.icon}}" width="24" height="24">
        <span class="effect-name">{{effect.name}}</span>
        <span class="effect-value">
          {{#if effect.isAdd}}+{{/if}}
          {{effect.value}}
          {{#if effect.isMultiply}}×{{/if}}
        </span>
        <div class="effect-controls">
          <a class="effect-control" data-action="edit"><i class="fas fa-edit"></i></a>
          <a class="effect-control" data-action="delete"><i class="fas fa-trash"></i></a>
        </div>
      </li>
      {{/each}}
      
      <a class="add-effect" data-characteristic="str">
        <i class="fas fa-plus"></i> Add Effect
      </a>
    </div>
  </div>
</div>
```

## 12. Sample Implementation of Effect Creation Dialog

```javascript
// In your actor sheet class
async _onAddEffect(event) {
  event.preventDefault();
  const characteristic = event.currentTarget.dataset.characteristic;
  
  // Create dialog data
  const dialogData = {
    characteristic: characteristic,
    modes: Object.entries(CONST.ACTIVE_EFFECT_MODES).reduce((obj, e) => {
      obj[e[1]] = game.i18n.localize(`EFFECT.MODE_${e[0]}`);
      return obj;
    }, {})
  };
  
  // Render dialog
  const html = await renderTemplate("systems/mysystem/templates/dialogs/add-effect.hbs", dialogData);
  
  return new Promise(resolve => {
    new Dialog({
      title: `Add Effect to ${characteristic.toUpperCase()}`,
      content: html,
      buttons: {
        create: {
          icon: '<i class="fas fa-check"></i>',
          label: "Create Effect",
          callback: html => {
            const form = html.querySelector("form");
            const formData = new FormDataExtended(form);
            const data = formData.object;
            
            // Create the new effect
            this.actor.createEmbeddedDocuments("ActiveEffect", [{
              label: data.label,
              icon: data.icon,
              changes: [{
                key: `system.characteristics.${characteristic}.value`,
                mode: parseInt(data.mode),
                value: data.value
              }],
              duration: data.duration ? { rounds: parseInt(data.duration) } : {}
            }]);
            
            resolve(true);
          }
        },
        cancel: {
          icon: '<i class="fas fa-times"></i>',
          label: "Cancel",
          callback: () => resolve(false)
        }
      },
      default: "create",
      close: () => resolve(false)
    }).render(true);
  });
}
```

These examples should give you a solid understanding of how to implement effect-ready data models in FoundryVTT using JavaScript, which you can then translate to Scala.js. The key elements are:

1. Structuring your data model with base and computed values
2. Registering effect change paths with FoundryVTT
3. Handling effect application with proper stacking and priorities
4. Creating a UI for managing effects

Let me know if you need additional clarification on any aspect of these examples!
