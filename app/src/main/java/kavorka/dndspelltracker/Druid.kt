package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val land = "Land"
const val moon = "Moon"

class Druid(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level = playerCharacter.level
        setSpellsFull(level)

        if (level >= 2) {
            subClasses.add(land)
            subClasses.add(moon)

            // Wild shape
            abilities.add(Ability("", "Wild Shape", 2, resetOnShort = true))

            // Sub class abilities
            val sub = playerCharacter.characterSubClass

            if (sub == land) {
                abilities.add(Ability("", "Natural Recovery", 1))
            }
        }
    }
}
