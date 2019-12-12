package kavorka.dndspelltracker.races

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

class HalfOrc(playerCharacter: PlayerCharacter) : Race(playerCharacter) {
    init {
        raceAbilities.add(Ability( "Relentless Endurance"))
    }
}