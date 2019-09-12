package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val knowledge = "Knowledege"
const val life = "Life"
const val light = "Light"
const val nature = "Nature"
const val tempest = "Tempest"
const val trickery = "Trickery"
const val war = "War"

class Cleric(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level = playerCharacter.level
        setSpellsFull(level)

        if (level >= 2) {
            subClasses.add(knowledge)
            subClasses.add(life)
            subClasses.add(light)
            subClasses.add(nature)
            subClasses.add(tempest)
            subClasses.add(trickery)
            subClasses.add(war)
        }
    }
}
