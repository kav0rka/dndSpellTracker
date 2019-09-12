package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val chain = "Chain"
const val blade = "Blade"
const val tome = "Tome"

class Warlock(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level = playerCharacter.level
        setSpells(level)

        if (level >= 3) {
            subClasses.add(chain)
            subClasses.add(blade)
            subClasses.add(tome)
        }
    }

    fun setSpells(level: Int) {
        if (level == 1)
            lvl1SpellMax = 1
        else if (level == 2)
            lvl1SpellMax = 2
        else if (level == 3 || level == 4) {
            lvl2SpellMax = 2
        } else if (level == 5 || level == 6) {
            lvl3SpellMax = 2
        } else if (level == 7 || level == 8) {
            lvl4SpellMax = 2
        } else if (level == 9 || level == 10) {
            lvl5SpellMax = 2
        } else if (level >= 11 && level <= 16) {
            lvl5SpellMax = 3
        } else if (level >= 17) {
            lvl5SpellMax = 4
        }
    }
}
