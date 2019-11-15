package kavorka.dndspelltracker

import androidx.lifecycle.ViewModel
import kavorka.dndspelltracker.data.Ability

class NewCharacterViewModel: ViewModel() {
    var characterClass: String=""
    var race: String=""
    var level: Int = 20
    var feats = mutableListOf<Ability>()

}