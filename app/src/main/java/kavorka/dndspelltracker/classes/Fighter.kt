package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val champion = "Champion"
const val battleMaster = "Battle Master"
const val eldritchKnight = "Eldritch Knight"

class Fighter(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        hitDie = 10
        val level = playerCharacter.level
        setSpells(level)

        abilities.add(Ability("", "Second Wind", 1, resetOnShort = true))
        if (level >= 2) {
            var surgeMax = 1
            if (level >= 17) surgeMax++
            abilities.add(Ability("", "Action Surge", surgeMax, resetOnShort = true))
        }

        if (level >= 3) {
            subClasses.add(champion)
            subClasses.add(battleMaster)
            subClasses.add(eldritchKnight)
        }

        if (level >= 9) {
            var max = 1
            if (level >= 13) max++
            if (level >= 17) max++
            abilities.add(Ability("", "Indomitable", max))
        }

        // Sub class abilities
        val sub = playerCharacter.characterSubClass

        if (sub == battleMaster) {
            var combatMax = 4
            if (level >= 7) combatMax++
            if (level >= 15) combatMax++
            abilities.add(Ability("", "Combat Superiority", combatMax, resetOnShort = true))
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
