package kavorka.dndspelltracker.Classes

import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val lore = "Lore"
const val valor = "Valor"

class Bard(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        val level = playerCharacter.level
        setSpellsFull(level)

        if (level >= 3) {
            subClasses.add(lore)
            subClasses.add(valor)
        }
    }
}
