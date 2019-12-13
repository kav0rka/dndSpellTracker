package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val berserker = "Berserker"
const val totemWarrior = "Totem Warrior"
const val ancestralGuardian = "Ancestral Guardian"
const val stormHerald = "Storm Herald"
const val zealot = "Zealot"

class Barbarian(playerCharacter: PlayerCharacter, subClass: String="", level: Int=1) : ClassMain(playerCharacter, subClass, level) {

    init {
        hitDie = 12

        var rageAmount = 2
        when (level) {
            in 3..5 -> rageAmount = 3
            in 6..11 -> rageAmount = 4
            in 12..16 -> rageAmount = 5
            in 17..19 -> rageAmount = 6
            20 -> rageAmount = 0
        }
        if (rageAmount > 0) abilities.add(Ability("Rage", max=rageAmount))

        if (level >= 3) {
            subClasses.add(berserker)
            subClasses.add(totemWarrior)
            subClasses.add(ancestralGuardian)
            subClasses.add(stormHerald)
            subClasses.add(zealot)
        }

        if (subClass == ancestralGuardian) {
            if (level >= 10) {
                abilities.add(Ability("Consult the spirits", resetOnShort = true))
            }
        } else if (subClass == zealot) {
            if (level >= 10) {
                abilities.add(Ability("Zealous Presence"))
            }

        }
    }
}
