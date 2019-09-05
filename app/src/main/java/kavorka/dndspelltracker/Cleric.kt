package kavorka.dndspelltracker

class Cleric internal constructor(character: CharacterViewModel) : CharacterClass() {

    init {
        setSpellsFull(character)
    }
}
