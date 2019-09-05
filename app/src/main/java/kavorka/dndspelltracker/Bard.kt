package kavorka.dndspelltracker

class Bard internal constructor(character: CharacterViewModel) : CharacterClass() {

    init {
        setSpellsFull(character)
    }
}
