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
        val level = playerCharacter.level
        setSpellsFull(level)

        //Arcane recovery
        abilities.add(Ability("", "Arcane Recovery", 1))

        if (level >= 2) {
            subClasses.add(abjuration)
            subClasses.add(conjuration)
            subClasses.add(divination)
            subClasses.add(enchantment)
            subClasses.add(evocation)
            subClasses.add(illusion)
            subClasses.add(necromancy)
            subClasses.add(transmutation)


            // Sub class abilities
            val sub = playerCharacter.characterSubClass

            if (sub == divination) {
                val portentMax = if (playerCharacter.level >= 14) 3 else  2
                abilities.add(Ability("", "Portent", portentMax))

                if (level >= 10) {
                    abilities.add(Ability("", "Third Eye", 1, resetOnShort = true))
                }
            } else if (sub == enchantment) {
                abilities.add(Ability("", "Hypnotic Gaze", 1))
                if (level >= 6) {
                    abilities.add(Ability("", "Instinctive Charm", 1))
                }
            } else if (sub == illusion) {
                if (level >= 10) {
                    abilities.add(Ability("", "Illusory Self", 1, resetOnShort = true))
                }
            } else if (sub == transmutation) {
                if (level >= 10) {
                    abilities.add(Ability("", "Shape Changer", 1, resetOnShort = true))
                }
            }
        }

    }

}
