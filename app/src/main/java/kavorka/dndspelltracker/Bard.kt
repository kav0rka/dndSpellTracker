package kavorka.dndspelltracker

class Bard internal constructor(character: Character) : CharacterClass(character) {

    init {
        setSpellsFull(character)
    }
}
