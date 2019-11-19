package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val berserker = "Berserker"
const val totemWarrior = "Totem Warrior"

class Barbarian(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level = playerCharacter.level
        if (level >= 3) {
            subClasses.add(berserker)
            subClasses.add(totemWarrior)
        }
    }
}
