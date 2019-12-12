package kavorka.dndspelltracker.classes

import kavorka.dndspelltracker.data.Ability
import kavorka.dndspelltracker.data.PlayerCharacter
import kavorka.dndspelltracker.getAbilityMod

// Sub classes
const val hunter = "Hunter"
const val beastMaster = "Beast Master"
const val gloomStalker = "Gloom Stalker"
const val horizonWalker = "Horizon Walker"
const val monsterSlayer = "Monster Slayer"

class Ranger(playerCharacter: PlayerCharacter) : CharacterClass(playerCharacter) {

    init {
        hitDie = 10
        val level = playerCharacter.level

        setSpellsSemi(level)

        if (level >= 3) {
            subClasses.add(hunter)
            subClasses.add(beastMaster)
            subClasses.add(gloomStalker)
            subClasses.add(horizonWalker)
            subClasses.add(monsterSlayer)
        }

        val sub = playerCharacter.characterSubClass
        if (sub == horizonWalker) {
            if (level >= 3) {
                abilities.add(Ability("Detect Portal", resetOnShort = true))
            }
            if (level >= 7) {
                abilities.add(Ability("Ethereal Step", resetOnShort = true))
            }
        } else if (sub == monsterSlayer) {
            if (level >= 3) {
                val wisMod = getAbilityMod(playerCharacter.wisdom)
                abilities.add(Ability("Hunter's Sense", max = wisMod))
            }
            if (level >= 11) {
                abilities.add(Ability("Magic-User's Nemesis", resetOnShort = true))
            }
        }
    }
}
