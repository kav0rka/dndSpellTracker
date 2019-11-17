package kavorka.dndspelltracker.Classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter
import kavorka.dndspelltracker.getAbilityMod

// Sub classes
const val devotion = "Devotion"
const val ancients = "Ancients"
const val vengeance = "Vengeance"

class Paladin(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level = playerCharacter.level
        setSpellsSemi(level)

        abilities.add(Ability("", "Divine Sense", getAbilityMod(playerCharacter.charisma) + 1))
        abilities.add(Ability("", "Lay on Hands", 5 * level))

        if (level >= 3) {
            subClasses.add(devotion)
            subClasses.add(ancients)
            subClasses.add(vengeance)

            abilities.add(Ability("", "Channel Divinity", 1, resetOnShort = true))

            if (level >= 14) {
                abilities.add(Ability("", "Cleansing Touch", getAbilityMod(playerCharacter.charisma)))
            }

            val sub = playerCharacter.characterSubClass
            if (sub == devotion) {
                if (level >= 20) {
                    abilities.add(Ability("", "Holy Nimbus", 1))
                }
            } else if (sub == ancients) {
                if (level >= 15) {
                    abilities.add(Ability("", "Undying Sentinel", 1))
                }
                if (level >= 20) {
                    abilities.add(Ability("", "Elder Champion", 1))
                }
            } else if (sub == vengeance) {
                if (level >= 20) {
                    abilities.add(Ability("", "Avenging Angel", 1))
                }
            }
        }


    }
}
