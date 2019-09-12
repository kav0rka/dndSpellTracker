package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val abjuration = "Abjuration"
const val conjuration = "Conjuration"
const val divination = "Divination"
const val enchantment = "Enchantment"
const val evocation = "Evocation"
const val illusion = "Illusion"
const val necromancy = "Necromancy"
const val transmutation = "Transmutation"

class Wizard(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        setSpellsFull(playerCharacter.level)

        subClasses.add(abjuration)
        subClasses.add(conjuration)
        subClasses.add(divination)
        subClasses.add(enchantment)
        subClasses.add(evocation)
        subClasses.add(illusion)
        subClasses.add(necromancy)
        subClasses.add(transmutation)

        if (playerCharacter.level >= 2) {
            val portent = Ability("", "Portent", 2)
            abilities.add(portent)
        }
    }

}
