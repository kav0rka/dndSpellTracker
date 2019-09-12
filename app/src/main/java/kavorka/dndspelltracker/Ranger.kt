package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.PlayerCharacter

class Ranger(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        setSpellsSemi(playerCharacter.level)
    }
}
