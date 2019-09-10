package kavorka.dndspelltracker

class Bard internal constructor(level: Int) : CharacterClass() {

    init {
        setSpellsFull(level)
    }
}
