package kavorka.dndspelltracker.races

import kavorka.dndspelltracker.data.PlayerCharacter

// Sub Races
const val hillDwarf = "Hill Dwarf"
const val mountainDwarf = "Mountain Dwarf"

class Dwarf(playerCharacter: PlayerCharacter) : Race(playerCharacter){
    init {
        subRaces.add(hillDwarf)
        subRaces.add(mountainDwarf)
    }
}