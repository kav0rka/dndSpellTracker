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

class Wizard(playerCharacter: PlayerCharacter, subClass: String="", level: Int=1) : ClassMain(playerCharacter, subClass, level) {

    init {
        hitDie = 6
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
            if (subClass == divination) {
                val portentMax = if (playerCharacter.level >= 14) 3 else  2
                abilities.add(Ability( "Portent", max=portentMax))

                if (level >= 10) {
                    abilities.add(Ability( "Third Eye", resetOnShort = true))
                }
            } else if (subClass == enchantment) {
                abilities.add(Ability( "Hypnotic Gaze"))
                if (level >= 6) {
                    abilities.add(Ability( "Instinctive Charm"))
                }
            } else if (subClass == illusion) {
                if (level >= 10) {
                    abilities.add(Ability( "Illusory Self", resetOnShort = true))
                }
            } else if (subClass == transmutation) {
                if (level >= 10) {
                    abilities.add(Ability( "Shape Changer", resetOnShort = true))
                }
            } else if (subClass == warMagic) {
                if (level >= 6) {
                    val surgeMax = getAbilityMod(playerCharacter.intelligence)
                    abilities.add(Ability("Power Surge", max = surgeMax, resetOnLong = 1))
                }
            }
        }
    }

}
