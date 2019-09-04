package kavorka.dndspelltracker

class Druid internal constructor(character: Character) : CharacterClass() {

    init {
        setSpellsFull(character)
    }
}
