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
        bewitchingWhispers -> Ability("", "Compulsion", 1, type= invocation)
        cloakOfFlies -> Ability("", cloakOfFlies, 1, resetOnShort = true)
        dreadfulWord -> Ability("", "Confusion", 1, type= invocation)
        ghostlyGaze -> Ability("", ghostlyGaze, 1, resetOnShort = true, type= invocation)
        giftOfTheDepths -> Ability("", "Water Breathing", 1, type= invocation)
        minionsOfChaos -> Ability("", "Conjure Elemental", 1, type= invocation)
        mireTheMind -> Ability("", "Slow", 1, type= invocation)
        sculptorOfFlesh -> Ability("", "Polymorph", 1, type= invocation)
        signOfIllOmen -> Ability("", "Bestow Curse", 1, type= invocation)
        thiefOfFiveFates -> Ability("", thiefOfFiveFates, 1, type= invocation)
        tombOfLevistus -> Ability("", tombOfLevistus, 1, resetOnShort = true, type= invocation)
        trickstersEscape -> Ability("", "Freedom of Movement", 1, type= invocation)
        else ->  Ability("", "Compulsion", 1, type= invocation)
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


class Warlock(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level = playerCharacter.level
        setSpells(level)

        subClasses.add(archFey)
        subClasses.add(fiend)
        subClasses.add(greatOldOne)
        subClasses.add(celestial)
        subClasses.add(hexblade)

        val sub = playerCharacter.characterSubClass

        if (level == 20) {
            abilities.add(Ability("", "Eldritch Master", 1))
        }

        if (sub == archFey) {
            abilities.add(Ability("", "Fey Presence", 1, resetOnShort = true))
            if (level >= 6) {
                abilities.add(Ability("", "Misty Escape", 1, resetOnShort = true))
            }
            if (level >= 14) {
                abilities.add(Ability("", "Dark Delirium",1, resetOnShort = true))
            }
        } else if (sub == fiend) {
            if (level >= 6) {
                abilities.add(Ability("", "Dark One's Own Luck", 1, resetOnShort = true))
            }
            if (level >= 14) {
                abilities.add(Ability("", "Hurl Through Hell", 1))
            }
        } else if (sub == greatOldOne) {
            if (level >= 6) {
                abilities.add(Ability("","Entropic Ward", 1, resetOnShort = true))
            }
        } else if (sub == celestial) {
            abilities.add(Ability("", "Healing Light", level+1))
            if (level >= 14) {
                abilities.add(Ability("", "Searing Vengeance", 1))
            }
        } else if (sub == hexblade) {
            abilities.add(Ability("", "Hexblade's Curse", 1, resetOnShort = true))
            if (level >= 6) {
                abilities.add(Ability("", "Accursed Specter", 1))
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
