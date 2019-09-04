package kavorka.dndspelltracker

class Cleric internal constructor(character: Character) : CharacterClass() {

    init {
        setSpellsFull(character)
    }
}
