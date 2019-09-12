package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val draconicBloodline = "Draconic Bloodline"
const val wildMagic = "Wild Magic"

class Sorcerer(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        setSpellsFull(playerCharacter.level)

        subClasses.add(draconicBloodline)
        subClasses.add(wildMagic)
    }
}
