import { CharacterDataModel } from './datamodel/character-data-model.js'

/**
 * Some addons that can't be implemented in scalajs
 *
 */
export const MythrasAddons = {
  registerDataModels: function () {
    console.log("Registering data models!")
    // CONFIG.Actor.dataModels.character = CharacterDataModel

    console.log("Model registered")
  }
}

