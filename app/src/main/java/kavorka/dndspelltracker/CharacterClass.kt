package kavorka.dndspelltracker

import android.util.Log
import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

open class CharacterClass(val playerCharacter: PlayerCharacter) {

    var lvl1SpellMax: Int=0
    var lvl2SpellMax: Int=0
    var lvl3SpellMax: Int=0
    var lvl4SpellMax: Int=0
    var lvl5SpellMax: Int=0
    var lvl6SpellMax: Int=0
    var lvl7SpellMax: Int=0
    var lvl8SpellMax: Int=0
    var lvl9SpellMax: Int=0

    var abilities = mutableListOf<Ability>()
    var subClasses = mutableListOf<String>()

    fun setSpellsFull(level: Int) {
        if (level >= 1) lvl1SpellMax = 2
        if (level >= 2) lvl1SpellMax++
        if (level >= 3) {
            lvl1SpellMax++
            lvl2SpellMax =2
        }
        if (level >= 4) {
            lvl2SpellMax++
        }
        if (level >= 5) {
            lvl3SpellMax = 2
        }
        if (level >= 6) {
            lvl2SpellMax++
            lvl3SpellMax++
        }
        if (level >= 7) {
            lvl4SpellMax = 1
        }
        if (level >= 8) {
            lvl4SpellMax++
        }
        if (level >= 9) {
            lvl5SpellMax = 1
        }
        if (level >= 10) {
            lvl5SpellMax++
        }
        if (level >= 11) {
            lvl6SpellMax = 1
        }
        if (level >= 13) {
            lvl7SpellMax = 1
        }
        if (level >= 15) {
            lvl8SpellMax = 1
        }
        if (level >= 17) {
            lvl9SpellMax = 1
        }
        if (level >= 18) {
            lvl5SpellMax++
        }
        if (level >= 19) {
            lvl6SpellMax++
        }
        if (level == 20) {
            lvl7SpellMax++
        }
    }

    fun setSpellsSemi(level: Int) {
        if (level >= 2) lvl1SpellMax = 2
        if (level >= 3) lvl1SpellMax++
        if (level >= 5) {
            lvl1SpellMax++
            lvl2SpellMax = 2
        }
        if (level >= 7) lvl2SpellMax++
        if (level >= 9) lvl3SpellMax = 2
        if (level >= 11) lvl3SpellMax++
        if (level >= 13) lvl4SpellMax = 1
        if (level >= 15) lvl4SpellMax++
        if (level >= 17) {
            lvl4SpellMax++
            lvl5SpellMax = 1
        } else if (level >= 19)lvl5SpellMax++
    }

    fun getSpellSlots(): IntArray {
        val slots = IntArray(9)
        slots[0] = lvl1SpellMax
        slots[1] = lvl2SpellMax
        slots[2] = lvl3SpellMax
        slots[3] = lvl4SpellMax
        slots[4] = lvl5SpellMax
        slots[5] = lvl6SpellMax
        slots[6] = lvl7SpellMax
        slots[7] = lvl8SpellMax
        slots[8] = lvl9SpellMax
        return slots
    }


}