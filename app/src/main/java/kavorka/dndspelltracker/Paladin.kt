package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val devotion = "Devotion"
const val ancients = "Ancients"
const val vengeance = "Vengeance"

class Paladin(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level = playerCharacter.level
        setSpellsSemi(level)

        if (level >= 3) {
            subClasses.add(devotion)
            subClasses.add(ancients)
            subClasses.add(vengeance)
        }
    }
}
