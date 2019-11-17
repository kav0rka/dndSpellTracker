package kavorka.dndspelltracker.races

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub Races
const val highElf = "High Elf"
const val woodElf = "Wood Elf"
const val drow = "Drow"


class Elf(playerCharacter: PlayerCharacter) : Race(playerCharacter) {
    init {
        subRaces.add(highElf)
        subRaces.add(woodElf)
        subRaces.add(drow)

        // Set abilities
        val sub = playerCharacter.subRace
        val level = playerCharacter.level
        if (sub == drow) {
            if (level >= 3) {
                raceAbilities.add(Ability("", "Faerie Fire", 1))
            }
            if (level >= 5) {
                raceAbilities.add(Ability("", "Darkness", 1))
            }
        }
    }
}