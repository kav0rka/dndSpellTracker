package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val champion = "Champion"
const val battleMaster = "Battle Master"
const val eldritchKnight = "Eldritch Knight"

class Fighter(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level = playerCharacter.level

        if (level >= 3) {
            subClasses.add(champion)
            subClasses.add(battleMaster)
            subClasses.add(eldritchKnight)
        }
    }
}
