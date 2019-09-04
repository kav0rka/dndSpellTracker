package kavorka.dndspelltracker

class Druid internal constructor(character: Character) : CharacterClass(character) {

    init {
        setSpellsFull(character)
    }
}
