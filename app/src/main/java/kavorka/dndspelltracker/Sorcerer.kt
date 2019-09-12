package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.PlayerCharacter

class Sorcerer(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        setSpellsFull(playerCharacter.level)
    }
}
