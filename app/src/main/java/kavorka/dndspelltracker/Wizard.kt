package kavorka.dndspelltracker

class Wizard internal constructor(level: Int) : CharacterClass() {

    init {
        setSpellsFull(level)
    }

}
