package kavorka.dndspelltracker

class Cleric internal constructor(character: Character) : CharacterClass(character) {

    init {
        setSpellsFull(character)
    }
}
