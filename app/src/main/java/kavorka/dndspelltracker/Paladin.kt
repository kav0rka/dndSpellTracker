package kavorka.dndspelltracker

class Paladin internal constructor(character: Character) : CharacterClass() {

    init {
        setSpellsSemi(character)
    }
}
