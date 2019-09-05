package kavorka.dndspelltracker

class Sorcerer internal constructor(character: CharacterViewModel) : CharacterClass() {

    init {
        setSpellsFull(character)
    }
}
