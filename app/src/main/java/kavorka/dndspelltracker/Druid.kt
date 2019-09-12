package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.PlayerCharacter

class Druid(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        setSpellsFull(playerCharacter.level)
    }
}
