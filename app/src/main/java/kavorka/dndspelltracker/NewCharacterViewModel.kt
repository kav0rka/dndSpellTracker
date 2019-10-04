package kavorka.dndspelltracker

import androidx.lifecycle.ViewModel
import kavorka.dndspelltracker.data.Ability

class NewCharacterViewModel: ViewModel() {
    var feats = mutableListOf<Ability>()

}