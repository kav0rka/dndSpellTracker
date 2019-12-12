package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter

// Sub classes
const val draconicBloodline = "Draconic Bloodline"
const val wildMagic = "Wild Magic"
const val divineSoul = "Divine Soul"
const val shadowMagic = "Shadow Magic"
const val stormSorcery = "Storm Sorcery"

class Sorcerer(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        hitDie = 6
        val level =playerCharacter.level
        setSpellsFull(level)

        subClasses.add(draconicBloodline)
        subClasses.add(wildMagic)
        subClasses.add(divineSoul)
        subClasses.add(shadowMagic)
        subClasses.add(stormSorcery)

        if (level >= 2) {
            abilities.add(Ability("Sorcery Points", max=level))
        }

        val sub = playerCharacter.characterSubClass
        if (sub == divineSoul) {
            abilities.add(Ability("Favored by the Gods", resetOnShort = true))
            if (level >= 18) {
                abilities.add(Ability("Unearthly Recovery"))
            }
        } else if (sub == shadowMagic) {
            abilities.add(Ability("Strength of the Grave"))
        } else if (sub == stormSorcery) {
            if (level >= 18) {
                abilities.add(Ability("Wind Soul", resetOnShort = true))
            }
        }
    }
}
