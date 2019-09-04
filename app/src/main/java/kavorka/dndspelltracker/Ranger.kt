package kavorka.dndspelltracker

class Ranger internal constructor(character: Character) : CharacterClass() {

    init {
        setSpellsSemi(character)
    }
}
