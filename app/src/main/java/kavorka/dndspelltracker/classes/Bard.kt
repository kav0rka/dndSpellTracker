package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter
import kavorka.dndspelltracker.getAbilityMod

// Sub classes
const val lore = "Lore"
const val valor = "Valor"

class Bard(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        hitDie = 8
        val level = playerCharacter.level
        setSpellsFull(level)
        var inspirationMax = getAbilityMod(playerCharacter.charisma)
        if (inspirationMax < 1) inspirationMax = 1
        abilities.add(Ability("", "Inspiration", inspirationMax, resetOnShort = level >= 5))

        if (level >= 3) {
            subClasses.add(lore)
            subClasses.add(valor)
        }
    }
}
