package kavorka.dndspelltracker

class Sorcerer internal constructor(character: Character) : CharacterClass() {

    init {
        setSpellsFull(character)
    }
}
