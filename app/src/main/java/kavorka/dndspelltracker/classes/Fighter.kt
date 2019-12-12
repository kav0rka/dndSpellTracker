package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter
import kavorka.dndspelltracker.getAbilityMod

// Sub classes
const val champion = "Champion"
const val battleMaster = "Battle Master"
const val eldritchKnight = "Eldritch Knight"
const val arcaneArcher = "Arcane Archer"
const val cavalier = "Cavalier"
const val samurai = "Samurai"

class Fighter(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        hitDie = 10
        val level = playerCharacter.level
        setSpells(level)

        abilities.add(Ability("Second Wind", resetOnShort = true))
        if (level >= 2) {
            var surgeMax = 1
            if (level >= 17) surgeMax++
            abilities.add(Ability("Action Surge", max=surgeMax, resetOnShort = true))
        }

        if (level >= 3) {
            subClasses.add(champion)
            subClasses.add(battleMaster)
            subClasses.add(eldritchKnight)
            subClasses.add(arcaneArcher)
            subClasses.add(cavalier)
            subClasses.add(samurai)
        }

        if (level >= 9) {
            var max = 1
            if (level >= 13) max++
            if (level >= 17) max++
            abilities.add(Ability("Indomitable", max=max))
        }

        // Sub class abilities
        val sub = playerCharacter.characterSubClass

        if (sub == battleMaster) {
            var combatMax = 4
            if (level >= 7) combatMax++
            if (level >= 15) combatMax++
            abilities.add(Ability("Combat Superiority", max=combatMax, resetOnShort = true))
        } else if (sub == arcaneArcher) {
            if (level >= 3) {
                abilities.add(Ability("Arcane Shot", max = 2, resetOnShort = true))
            }
        } else if (sub == cavalier) {
            if (level >= 3) {
                val markMax = getAbilityMod(playerCharacter.strength)
                abilities.add(Ability("Unwavering Mark", max=markMax))
            }
            if (level >= 7) {
                val maneuverMax = getAbilityMod(playerCharacter.constitution)
                abilities.add(Ability("Warding Maneuver", max=maneuverMax))
            }
        } else if (sub == samurai) {
            if (level >= 3) {
                abilities.add(Ability("Fighting Spirit", max = 3))
            }
            if (level >= 18) {
                abilities.add(Ability("Strength before Death"))
            }
        }
    }


    private fun setSpells(level: Int) {
        if (playerCharacter.characterSubClass == eldritchKnight) {
            if (level >= 3) lvl1SpellMax = 2
            if (level >= 4) lvl1SpellMax++
            if (level >= 7) {
                lvl1SpellMax++
                lvl2SpellMax = 2
            }
            if (level >= 10) lvl2SpellMax++
            if (level >= 13) lvl3SpellMax = 2
            if (level >= 16) lvl3SpellMax++
            if (level >= 19) lvl4SpellMax = 1
        }
    }
}
