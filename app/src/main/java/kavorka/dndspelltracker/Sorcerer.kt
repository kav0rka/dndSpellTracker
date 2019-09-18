package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val draconicBloodline = "Draconic Bloodline"
const val wildMagic = "Wild Magic"

class Sorcerer(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level =playerCharacter.level
        setSpellsFull(level)

        subClasses.add(draconicBloodline)
        subClasses.add(wildMagic)

        if (level >= 2) {
            abilities.add(Ability("", "Sorcery Points", level))
        }
    }
}
