package kavorka.dndspelltracker

class Wizard internal constructor(character: CharacterViewModel) : CharacterClass() {

    init {
        setSpellsFull(character)
    }

}
