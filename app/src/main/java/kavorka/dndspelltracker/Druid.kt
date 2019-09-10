package kavorka.dndspelltracker

class Druid internal constructor(level: Int) : CharacterClass() {

    init {
        setSpellsFull(level)
    }
}
