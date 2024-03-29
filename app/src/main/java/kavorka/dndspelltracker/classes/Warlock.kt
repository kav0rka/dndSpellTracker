package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val archFey = "Arch Fey"
const val fiend = "Fiend"
const val greatOldOne = "Great Old One"
const val celestial = "Celestial"
const val hexblade = "Hexblade"

// Invocations (With abilities attached)
const val invocation = "invocation"
const val bewitchingWhispers = "Bewitching Whispers"
const val dreadfulWord = "Dreadful Word"
const val minionsOfChaos = "Minions of Chaos"
const val mireTheMind = "Mire the mind"
const val sculptorOfFlesh = "Sculptor of Flesh"
const val signOfIllOmen = "Sign of Ill Omen"
const val thiefOfFiveFates = "Thief of Five Fates"
const val cloakOfFlies = "Cloak of Flies"
const val ghostlyGaze = "Ghostly Gaze"
const val giftOfTheDepths = "Gift of the Depths"
const val tombOfLevistus = "Tomb of Levistus"
const val trickstersEscape = "Trickster's Escape"

// Get Invocation
fun getInvocation(invocationName: String) : Ability {
    return when(invocationName) {
        bewitchingWhispers -> Ability("Compulsion", type= invocation)
        cloakOfFlies -> Ability(cloakOfFlies, resetOnShort = true)
        dreadfulWord -> Ability( "Confusion", 1, type= invocation)
        ghostlyGaze -> Ability(ghostlyGaze, resetOnShort = true, type= invocation)
        giftOfTheDepths -> Ability("Water Breathing", type= invocation)
        minionsOfChaos -> Ability( "Conjure Elemental", type= invocation)
        mireTheMind -> Ability( "Slow", type= invocation)
        sculptorOfFlesh -> Ability( "Polymorph", type= invocation)
        signOfIllOmen -> Ability( "Bestow Curse", type= invocation)
        thiefOfFiveFates -> Ability( thiefOfFiveFates, type= invocation)
        tombOfLevistus -> Ability( tombOfLevistus, resetOnShort = true, type= invocation)
        trickstersEscape -> Ability( "Freedom of Movement", type= invocation)
        else ->  Ability( "Compulsion", type= invocation)
    }
}


fun availableInvocations(level: Int=20) : List<String> {
    val available = mutableListOf<String>()

    available.add(thiefOfFiveFates)
    if (level >= 5) {
        available.add(cloakOfFlies)
        available.add(giftOfTheDepths)
        available.add(mireTheMind)
        available.add(signOfIllOmen)
        available.add(tombOfLevistus)
    }
    if (level >= 7) {
        available.add(bewitchingWhispers)
        available.add(dreadfulWord)
        available.add(ghostlyGaze)
        available.add(sculptorOfFlesh)
        available.add(trickstersEscape)
    }
    if (level >= 9) {
        available.add(minionsOfChaos)
    }

    return available
}


class Warlock(playerCharacter: PlayerCharacter, subClass: String="", level: Int=1) : ClassMain(playerCharacter, subClass, level) {

    init {
        hitDie = 8
        setSpells(level)

        subClasses.add(archFey)
        subClasses.add(fiend)
        subClasses.add(greatOldOne)
        subClasses.add(celestial)
        subClasses.add(hexblade)



        if (level == 20) {
            abilities.add(Ability("Eldritch Master"))
        }

        // Sub class abilities
        if (subClass == archFey) {
            abilities.add(Ability("Fey Presence", resetOnShort = true))
            if (level >= 6) {
                abilities.add(Ability("Misty Escape", resetOnShort = true))
            }
            if (level >= 14) {
                abilities.add(Ability("Dark Delirium", resetOnShort = true))
            }
        } else if (subClass == fiend) {
            if (level >= 6) {
                abilities.add(Ability("Dark One's Own Luck", resetOnShort = true))
            }
            if (level >= 14) {
                abilities.add(Ability("Hurl Through Hell"))
            }
        } else if (subClass == greatOldOne) {
            if (level >= 6) {
                abilities.add(Ability("Entropic Ward", resetOnShort = true))
            }
        } else if (subClass == celestial) {
            abilities.add(Ability("Healing Light", max=level+1))
            if (level >= 14) {
                abilities.add(Ability("Searing Vengeance"))
            }
        } else if (subClass == hexblade) {
            abilities.add(Ability("Hexblade's Curse", resetOnShort = true))
            if (level >= 6) {
                abilities.add(Ability( "Accursed Specter"))
            }
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

    }


}
