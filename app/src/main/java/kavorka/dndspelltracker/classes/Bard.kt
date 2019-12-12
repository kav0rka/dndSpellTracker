package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter
import kavorka.dndspelltracker.getAbilityMod

// Sub classes
const val lore = "Lore"
const val valor = "Valor"
const val glamour = "Glamour"
const val swords = "Swords"
const val whispers = "Whispers"

class Bard(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        hitDie = 8
        val level = playerCharacter.level
        setSpellsFull(level)
        var inspirationMax = getAbilityMod(playerCharacter.charisma)
        abilities.add(Ability("Inspiration", max=inspirationMax, resetOnShort = level >= 5))

        if (level >= 3) {
            subClasses.add(lore)
            subClasses.add(valor)
            subClasses.add(glamour)
            subClasses.add(swords)
            subClasses.add(whispers)
        }

        val subClass = playerCharacter.characterSubClass
        if (subClass == glamour) {
            if (level >= 3) abilities.add(Ability("Enthralling Performance", resetOnShort = true))
            if (level >= 6) abilities.add(Ability("Mantle of Majesty"))
            if (level >=14) abilities.add(Ability("Unbreakable Majesty", resetOnShort = true))
        } else if (subClass == whispers) {
            if (level >= 3) abilities.add(Ability("Words of Terror", resetOnShort = true))
            if (level >= 6) abilities.add(Ability("Mantle of Whispers", resetOnShort = true))
            if (level >= 14) abilities.add(Ability("Shadow Lore"))
        }
    }
}
