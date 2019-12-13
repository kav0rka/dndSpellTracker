package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val openHand = "Open Hand"
const val shadow = "Shadow"
const val fourElements = "Four Elements"
const val kensei = "Kensei"
const val sunSoul = "Sun Soul"

class Monk(playerCharacter: PlayerCharacter, subClass: String="", level: Int=1) : ClassMain(playerCharacter, subClass, level) {

    init {
        hitDie = 8

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
            if (subClass == openHand) {
                abilities.add(Ability("Wholeness of body"))
            }
        }


    }
}
