package kavorka.dndspelltracker

class Sorcerer internal constructor(level: Int) : CharacterClass() {

    init {
        setSpellsFull(level)
    }
}
