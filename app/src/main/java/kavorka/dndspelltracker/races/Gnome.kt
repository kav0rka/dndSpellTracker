package kavorka.dndspelltracker.races

import kavorka.dndspelltracker.data.PlayerCharacter

// Sub races
const val forestGnome = "Forest Gnome"
const val rockGnome = "Rock Gnome"

class Gnome(playerCharacter: PlayerCharacter) : Race(playerCharacter) {
    init {
        subRaces.add(forestGnome)
        subRaces.add(rockGnome)
    }
}