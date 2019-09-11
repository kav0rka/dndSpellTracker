package kavorka.dndspelltracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kavorka.dndspelltracker.data.Spells


class CharacterViewModel: ViewModel() {
    private var spells: LiveData<List<Spells>>? = null
    fun getSpells(name: String) : LiveData<List<Spells>> {
        if (spells == null) {
            spells = db.spellsDao().getSpellsByCharacterLive(name)
        }
        return spells!!
    }

    var hitPoints: Int =0
    var level: Int = 5
    var name: String = ""
    var charClass = Wizard(level)

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