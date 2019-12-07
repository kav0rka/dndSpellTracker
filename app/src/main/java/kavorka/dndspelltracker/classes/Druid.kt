package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter
import kavorka.dndspelltracker.getAbilityMod

// Sub classes
const val land = "Land"
const val moon = "Moon"
const val dreams = "Dreams"
const val shepard = "Shepard"

class Druid(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        hitDie = 8
        val level = playerCharacter.level
        setSpellsFull(level)

        if (level >= 2) {
            subClasses.add(land)
            subClasses.add(moon)
            subClasses.add(dreams)
            subClasses.add(shepard)

            // Wild shape
            abilities.add(Ability("", "Wild Shape", 2, resetOnShort = true))

            // Sub class abilities
            val sub = playerCharacter.characterSubClass

            if (sub == land) {
                abilities.add(Ability("", "Natural Recovery", 1))
            } else if (sub == dreams) {
                val wisdomMod = getAbilityMod(playerCharacter.wisdom)
                if (level >= 2) abilities.add(Ability("", "Balm of the Summer Court", level))
                if (level >= 6) abilities.add(Ability("", "Hidden Paths", wisdomMod))
                if (level >= 14) abilities.add(Ability("", "Walker in Dreams", 1))
            } else if (sub == shepard) {
                if (level >= 14) abilities.add(Ability("", "Faithful Summons", 1))
            }
        }
    }
}
