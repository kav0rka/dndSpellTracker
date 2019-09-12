package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val hunter = "Hunter"
const val beastMaster = "Beast Master"

class Ranger(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level = playerCharacter.level

        setSpellsSemi(level)

        if (level >= 3) {
            subClasses.add(hunter)
            subClasses.add(beastMaster)
        }
    }
}
