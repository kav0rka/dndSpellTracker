package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val openHand = "Open Hand"
const val shadow = "Shadow"
const val fourElements = "Four Elements"

class Monk(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level = playerCharacter.level

        if (level >= 3) {
            subClasses.add(openHand)
            subClasses.add(shadow)
            subClasses.add(fourElements)
        }
    }
}
