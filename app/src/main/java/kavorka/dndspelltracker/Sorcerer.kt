package kavorka.dndspelltracker

class Sorcerer internal constructor(character: Character) : CharacterClass(character) {

    init {
        setSpellsFull(character)
    }
}
