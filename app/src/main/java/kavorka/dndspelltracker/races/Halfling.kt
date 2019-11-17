package kavorka.dndspelltracker.races

import kavorka.dndspelltracker.data.PlayerCharacter


// Sub races
const val lightfoot = "Lightfoot"
const val stout = "Stout"

class Halfling(playerCharacter: PlayerCharacter) : Race(playerCharacter) {
    init {
        subRaces.add(lightfoot)
        subRaces.add(stout)
    }
}