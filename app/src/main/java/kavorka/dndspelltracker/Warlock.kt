package kavorka.dndspelltracker

class Warlock internal constructor(character: Character) : CharacterClass(character) {

    init {
        setSpells(character)
    }
    fun setSpells(character: Character) {
        val charLvl = character.level

        if (charLvl == 1)
            lvl1SpellMax = 1
        else if (charLvl == 2)
            lvl1SpellMax = 2
        else if (charLvl == 3 || charLvl == 4) {
            lvl2SpellMax = 2
        } else if (charLvl == 5 || charLvl == 6) {
            lvl3SpellMax = 2
        } else if (charLvl == 7 || charLvl == 8) {
            lvl4SpellMax = 2
        } else if (charLvl == 9 || charLvl == 10) {
            lvl5SpellMax = 2
        } else if (charLvl >= 11 && charLvl <= 16) {
            lvl5SpellMax = 3
        } else if (charLvl >= 17) {
            lvl5SpellMax = 4
        }
    }
}
