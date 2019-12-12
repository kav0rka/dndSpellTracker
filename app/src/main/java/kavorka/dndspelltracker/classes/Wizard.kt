package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter
import kavorka.dndspelltracker.getAbilityMod

// Sub classes
const val abjuration = "Abjuration"
const val conjuration = "Conjuration"
const val divination = "Divination"
const val enchantment = "Enchantment"
const val evocation = "Evocation"
const val illusion = "Illusion"
const val necromancy = "Necromancy"
const val transmutation = "Transmutation"
const val warMagic = "War Magic"

class Wizard(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        hitDie = 6
        val level = playerCharacter.level
        setSpellsFull(level)

        //Arcane recovery
        abilities.add(Ability( "Arcane Recovery"))

        if (level >= 2) {
            subClasses.add(abjuration)
            subClasses.add(conjuration)
            subClasses.add(divination)
            subClasses.add(enchantment)
            subClasses.add(evocation)
            subClasses.add(illusion)
            subClasses.add(necromancy)
            subClasses.add(transmutation)
            subClasses.add(warMagic)


            // Sub class abilities
            val sub = playerCharacter.characterSubClass

            if (sub == divination) {
                val portentMax = if (playerCharacter.level >= 14) 3 else  2
                abilities.add(Ability( "Portent", max=portentMax))

                if (level >= 10) {
                    abilities.add(Ability( "Third Eye", resetOnShort = true))
                }
            } else if (sub == enchantment) {
                abilities.add(Ability( "Hypnotic Gaze"))
                if (level >= 6) {
                    abilities.add(Ability( "Instinctive Charm"))
                }
            } else if (sub == illusion) {
                if (level >= 10) {
                    abilities.add(Ability( "Illusory Self", resetOnShort = true))
                }
            } else if (sub == transmutation) {
                if (level >= 10) {
                    abilities.add(Ability( "Shape Changer", resetOnShort = true))
                }
            } else if (sub == warMagic) {
                if (level >= 6) {
                    val surgeMax = getAbilityMod(playerCharacter.intelligence)
                    abilities.add(Ability("Power Surge", max = surgeMax, resetOnLong = 1))
                }
            }
        }

    }

}
