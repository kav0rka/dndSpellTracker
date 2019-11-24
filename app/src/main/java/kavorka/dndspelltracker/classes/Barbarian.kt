package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val berserker = "Berserker"
const val totemWarrior = "Totem Warrior"
const val ancestralGuardian = "Ancestral Guardian"
const val stormHerald = "Storm Herald"
const val zealot = "Zealot"

class Barbarian(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        hitDie = 12

        val level = playerCharacter.level
        if (level >= 3) {
            subClasses.add(berserker)
            subClasses.add(totemWarrior)
            subClasses.add(ancestralGuardian)
            subClasses.add(stormHerald)
            subClasses.add(zealot)
        }
    }
}
