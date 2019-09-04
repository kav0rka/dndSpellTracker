package kavorka.dndspelltracker

class Ranger internal constructor(character: Character) : CharacterClass(character) {

    init {
        setSpellsSemi(character)
    }
}
