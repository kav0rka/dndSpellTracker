package kavorka.dndspelltracker

class Ranger internal constructor(character: CharacterViewModel) : CharacterClass() {

    init {
        setSpellsSemi(character)
    }
}
