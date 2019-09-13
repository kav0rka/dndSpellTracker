package kavorka.dndspelltracker

import androidx.lifecycle.ViewModel
import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.Spells


class CharacterViewModel: ViewModel() {
    var spells = mutableListOf<Spells>()
    var abilities = mutableListOf<Ability>()
    var hitPoints: Int =0


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



    fun doShortRest() {
        abilities.forEach {
            if (it.resetOnShort) it.used = 0
        }
    }

    fun doLongRest() {
        abilities.forEach {
            it.used = 0
        }
        resetSpells()
    }

    fun resetSpells() {
        spells.forEach {
            it.used = 0
        }
    }

}