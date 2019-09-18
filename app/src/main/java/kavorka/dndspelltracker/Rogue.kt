package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val thief = "Thief"
const val assassin = "Assassin"
const val arcaneTrickster = "Arcane Trickster"

class Rogue(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level  = playerCharacter.level
        setSpells(level)
        if (level >=3) {
            subClasses.add(thief)
            subClasses.add(assassin)
            subClasses.add(arcaneTrickster)
        }
        val sub = playerCharacter.characterSubClass
        if (sub == arcaneTrickster) {
            if (level >= 17) {
                abilities.add(Ability("", "Spell Thief", 1))
            }
        }
    }


    private fun setSpells(level: Int) {
        if (playerCharacter.characterSubClass == arcaneTrickster) {
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
