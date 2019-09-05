package kavorka.dndspelltracker

class Paladin internal constructor(character: CharacterViewModel) : CharacterClass() {

    init {
        setSpellsSemi(character)
    }
}
