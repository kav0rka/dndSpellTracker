package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
val abjuration = "Abjuration"
val conjuration = "Conjuration"
val divination = "Divination"
val enchantment = "Enchantment"
val evocation = "Evocation"
val illusion = "Illusion"
val necromancy = "Necromancy"
val transmutation = "Transmutation"

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
