package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.PlayerCharacter

class Cleric(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        setSpellsFull(playerCharacter.level)
    }
}
