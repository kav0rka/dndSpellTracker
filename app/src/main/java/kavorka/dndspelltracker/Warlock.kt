package kavorka.dndspelltracker

class Warlock internal constructor(level: Int) : CharacterClass() {

    init {
        setSpells(level)
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
