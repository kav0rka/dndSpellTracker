package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val thief = "Thief"
const val assassin = "Assassin"
const val arcaneTrickster = "Arcane Trickster"

class Rogue(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level  = playerCharacter.level

        if (level >=3) {
            subClasses.add(thief)
            subClasses.add(assassin)
            subClasses.add(arcaneTrickster)
        }
    }
}
