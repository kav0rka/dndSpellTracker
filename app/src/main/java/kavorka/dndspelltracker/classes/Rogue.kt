package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter
import kavorka.dndspelltracker.getAbilityMod

// Sub classes
const val thief = "Thief"
const val assassin = "Assassin"
const val arcaneTrickster = "Arcane Trickster"
const val inquisitive = "Inquisitive"
const val mastermind = "Mastermind"
const val scout = "Scout"
const val swashbuckler = "Swashbuckler"

class Rogue(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        hitDie = 8
        val level  = playerCharacter.level
        setSpells(level)
        if (level >=3) {
            subClasses.add(thief)
            subClasses.add(assassin)
            subClasses.add(arcaneTrickster)
            subClasses.add(inquisitive)
            subClasses.add(mastermind)
            subClasses.add(scout)
            subClasses.add(swashbuckler)
        }
        val sub = playerCharacter.characterSubClass
        if (sub == arcaneTrickster) {
            if (level >= 17) {
                abilities.add(Ability("Spell Thief"))
            }
        } else if (sub == inquisitive) {
            if (level >= 13) {
                val eyeMax = getAbilityMod(playerCharacter.wisdom)
                abilities.add(Ability("Unerring Eye", max = eyeMax))
            }
        } else if (sub == swashbuckler) {
            if (level >=17) {
                abilities.add(Ability("Master Duelist", resetOnShort = true))
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
