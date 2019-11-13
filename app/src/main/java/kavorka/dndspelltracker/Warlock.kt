package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val chain = "Chain"
const val blade = "Blade"
const val tome = "Tome"

// Pacts

// Invocations (With abilities attached)
const val invocation = "invocation"
const val bewitchingWhispers = "Bewitching Whispers (Compulsion)"
const val dreadfulWord = "Dreadful Word (Confusion)"
const val minionsOfChaos = "Minions of Chaos (Conjure Elemental)"
const val mireTheMind = "Mire the mind (Slow)"
const val sculptorOfFlesh = "Sculptor of Flesh (Polymorph)"
const val signOfIllOmen = "Sign of Ill Omen (Bestow Curse)"
const val thiefOfFiveFates = "Thief of Five Fates"

// Get Invocation
fun getInvocation(invocationName: String) : Ability {
    return when(invocationName) {
        bewitchingWhispers -> Ability("", bewitchingWhispers, 1, type=invocation)
        dreadfulWord -> Ability("", dreadfulWord, 1, type=invocation)
        minionsOfChaos -> Ability("", minionsOfChaos, 1, type=invocation)
        mireTheMind -> Ability("", mireTheMind, 1, type=invocation)
        sculptorOfFlesh -> Ability("", sculptorOfFlesh, 1, type=invocation)
        signOfIllOmen -> Ability("", signOfIllOmen, 1, type=invocation)
        thiefOfFiveFates -> Ability("", thiefOfFiveFates, 1, type=invocation)
        else ->  Ability("", bewitchingWhispers, 1, type=invocation)
    }
}


fun availableInvocations(level: Int=20) : List<String>{
    val available = mutableListOf<String>()

    available.add(thiefOfFiveFates)
    if (level >= 5) {
        available.add(mireTheMind)
        available.add(signOfIllOmen)
    }
    if (level >= 7) {
        available.add(bewitchingWhispers)
        available.add(dreadfulWord)
        available.add(sculptorOfFlesh)
    }
    if (level >= 9) {
        available.add(minionsOfChaos)
    }

    return available
}


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

    private fun setSpells(level: Int) {
        if (level == 1)
            lvl1SpellMax = 1
        else if (level == 2)
            lvl1SpellMax = 2
        else if (level in 3..4) {
            lvl2SpellMax = 2
        } else if (level in 5..6) {
            lvl3SpellMax = 2
        } else if (level in 7..8) {
            lvl4SpellMax = 2
        } else if (level in 9..10) {
            lvl5SpellMax = 2
        } else if (level in 11..16) {
            lvl5SpellMax = 3
        } else if (level >= 17) {
            lvl5SpellMax = 4
        }
    }


}
