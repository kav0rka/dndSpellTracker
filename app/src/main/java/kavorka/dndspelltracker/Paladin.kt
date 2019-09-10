package kavorka.dndspelltracker

class Paladin internal constructor(level: Int) : CharacterClass() {

    init {
        setSpellsSemi(level)
    }
}
