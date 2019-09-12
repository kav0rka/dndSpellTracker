package kavorka.dndspelltracker

import androidx.lifecycle.ViewModel
import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.Spells


class CharacterViewModel: ViewModel() {
    var spells = mutableListOf<Spells>()
    var abilities = mutableListOf<Ability>()

    var hitPoints: Int =0
    var level: Int = 5
    var name: String = ""


    fun useSpell(lvl: Int) {
        val spell = spells[lvl]
        if (spell.used < spell.max) {
            spell.used++
        }
    }

    fun unUseSpell(lvl: Int) {
        val spell = spells[lvl]
        if (spell.used > 0) {
            spell.used--
        }
    }

    fun useAbility(position: Int) {
        val ability = abilities[position]
        if (ability.used < ability.max) {
            ability.used++
        }
    }

    fun unUseAbility(position: Int) {
        val ability = abilities[position]
        if (ability.used > 0) {
            ability.used--
        }
    }



    fun doShortRest() {}

    fun doLongRest() {
        resetSpells()
    }

    fun resetSpells() {
        spells.forEach {
            it.used = 0
        }
    }

}