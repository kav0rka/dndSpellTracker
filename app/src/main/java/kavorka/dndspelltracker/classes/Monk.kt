package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val openHand = "Open Hand"
const val shadow = "Shadow"
const val fourElements = "Four Elements"
const val kensei = "Kensei"
const val sunSoul = "Sun Soul"

class Monk(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        hitDie = 8
        val level = playerCharacter.level

        // Ki points
        if (level >= 2) {
            abilities.add(Ability("Ki", max=level, resetOnShort = true))
        }

        if (level >= 3) {
            subClasses.add(openHand)
            subClasses.add(shadow)
            subClasses.add(fourElements)
            subClasses.add(kensei)
            subClasses.add(sunSoul)

            // Sub class abilities
            val sub = playerCharacter.characterSubClass
            if (sub == openHand) {
                abilities.add(Ability("Wholeness of body"))
            }
        }


    }
}
