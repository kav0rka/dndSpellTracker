package kavorka.dndspelltracker

import kavorka.dndspelltracker.data.PlayerCharacter

class Paladin(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        setSpellsSemi(playerCharacter.level)
    }
}
