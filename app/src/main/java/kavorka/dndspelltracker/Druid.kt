package kavorka.dndspelltracker

class Druid internal constructor(character: CharacterViewModel) : CharacterClass() {

    init {
        setSpellsFull(character)
    }
}
