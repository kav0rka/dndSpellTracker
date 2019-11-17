package kavorka.dndspelltracker.Classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val openHand = "Open Hand"
const val shadow = "Shadow"
const val fourElements = "Four Elements"

class Monk(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level = playerCharacter.level

        // Ki points
        if (level >= 2) {
            abilities.add(Ability("", "Ki", level, resetOnShort = true))
        }

        if (level >= 3) {
            subClasses.add(openHand)
            subClasses.add(shadow)
            subClasses.add(fourElements)

            // Sub class abilities
            val sub = playerCharacter.characterSubClass
            if (sub == openHand) {
                abilities.add(Ability("", "Wholeness of body", 1))
            }
        }


    }
}
