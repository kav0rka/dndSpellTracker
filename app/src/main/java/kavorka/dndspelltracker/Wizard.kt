package kavorka.dndspelltracker

class Wizard internal constructor(character: Character) : CharacterClass(character) {

    init {
        setSpellsFull(character)
    }

}
