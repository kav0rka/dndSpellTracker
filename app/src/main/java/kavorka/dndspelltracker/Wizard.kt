package kavorka.dndspelltracker

class Wizard internal constructor(character: Character) : CharacterClass() {

    init {
        setSpellsFull(character)
    }

}
