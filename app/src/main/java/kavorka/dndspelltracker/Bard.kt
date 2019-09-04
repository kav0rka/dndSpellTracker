package kavorka.dndspelltracker

class Bard internal constructor(character: Character) : CharacterClass() {

    init {
        setSpellsFull(character)
    }
}
