package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter
import kavorka.dndspelltracker.getAbilityMod

// Sub classes
const val devotion = "Devotion"
const val ancients = "Ancients"
const val vengeance = "Vengeance"
const val conquest = "Conquest"
const val redemption = "Redemption"

class Paladin(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        hitDie = 10
        val level = playerCharacter.level
        val charMod = getAbilityMod(playerCharacter.charisma)
        setSpellsSemi(level)

        abilities.add(Ability("Divine Sense", max=charMod + 1))
        abilities.add(Ability("Lay on Hands", max=5 * level))

        if (level >= 3) {
            subClasses.add(devotion)
            subClasses.add(ancients)
            subClasses.add(vengeance)
            subClasses.add(conquest)
            subClasses.add(redemption)

            abilities.add(Ability("Channel Divinity", resetOnShort = true))

            if (level >= 14) {
                abilities.add(Ability("Cleansing Touch", max=charMod))
            }

            val sub = playerCharacter.characterSubClass
            if (sub == devotion) {
                if (level >= 20) {
                    abilities.add(Ability("Holy Nimbus"))
                }
            } else if (sub == ancients) {
                if (level >= 15) {
                    abilities.add(Ability("Undying Sentinel"))
                }
                if (level >= 20) {
                    abilities.add(Ability("Elder Champion"))
                }
            } else if (sub == vengeance) {
                if (level >= 20) {
                    abilities.add(Ability("Avenging Angel"))
                }
            } else if (sub == conquest) {
                if (level == 20) {
                    abilities.add(Ability("Invincible Conqueror"))
                }
            }
        }


    }
}
