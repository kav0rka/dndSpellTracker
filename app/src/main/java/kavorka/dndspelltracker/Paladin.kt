package kavorka.dndspelltracker

class Paladin internal constructor(character: Character) : CharacterClass(character) {

    init {
        setSpellsSemi(character)
    }
}
