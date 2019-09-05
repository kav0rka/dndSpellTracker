package kavorka.dndspelltracker

import androidx.lifecycle.ViewModel


class CharacterViewModel : ViewModel() {
    var hitPoints: Int =0
    var level: Int = 1
    var name: String = ""
    var charClass = Wizard(this)

    // Stats
    // ADD stats!

    var spellsUsed = IntArray(9)



    fun getSpellSlots(): IntArray { return charClass.getSpellSlots()}

    fun useSpell(lvl: Int) {
        val used = spellsUsed[lvl]
        if (used < getSpellSlots()[lvl]) {
            spellsUsed[lvl] = used + 1
        }
    }


    fun unuseSpell(lvl: Int) {
        val used = spellsUsed[lvl]
        if (used > 0) {
            spellsUsed[lvl] = used - 1
        }
    }


    fun doShortRest() {}

    fun doLongRest() {
        resetSpells()
    }

    fun resetSpells() {
        spellsUsed = IntArray(9)
    }

}