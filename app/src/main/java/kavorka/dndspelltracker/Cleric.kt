package kavorka.dndspelltracker

class Cleric internal constructor(level: Int) : CharacterClass() {

    init {
        setSpellsFull(level)
    }
}
