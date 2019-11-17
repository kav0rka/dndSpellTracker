package kavorka.dndspelltracker.races

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

class Dragonborn(playerCharacter: PlayerCharacter) : Race(playerCharacter) {
    init {
        raceAbilities.add(Ability("", "Breath Weapon", 1, resetOnShort = true))
    }
}