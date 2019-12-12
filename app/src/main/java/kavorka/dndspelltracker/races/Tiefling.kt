package kavorka.dndspelltracker.races

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

class Tiefling(playerCharacter: PlayerCharacter) : Race(playerCharacter) {
    init {
        val level = playerCharacter.level
        if (level >= 3) {
            raceAbilities.add(Ability( "Hellish Rebuke"))
        }
        if (level >= 5) {
            raceAbilities.add(Ability( "Darkness"))
        }
    }
}