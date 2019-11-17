package kavorka.dndspelltracker.races

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

open class Race(plyerCharacter: PlayerCharacter) {
    var subRaces = mutableListOf<String>()
    var raceAbilities = mutableListOf<Ability>()
}