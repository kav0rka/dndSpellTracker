package kavorka.dndspelltracker

class Ranger internal constructor(level: Int) : CharacterClass() {

    init {
        setSpellsSemi(level)
    }
}
