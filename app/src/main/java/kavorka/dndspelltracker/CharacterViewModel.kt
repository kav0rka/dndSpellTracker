package kavorka.dndspelltracker

import android.util.Log
import androidx.lifecycle.ViewModel
import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.Spells


class CharacterViewModel: ViewModel() {
    var spells = mutableListOf<Spells>()
    var abilities = mutableListOf<Ability>()
    var hitPoints: Int =0
    var characterClass: String=""


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
        if (characterClass == warlock) {
            resetSpells()
        }
    }

    fun doLongRest() {
        abilities.forEach {
            it.used -= it.resetOnLong
            if (it.used <0) it.used = 0
        }
        resetSpells()
    }

    fun resetSpells() {
        spells.forEach {
            it.used = 0
        }
    }

}