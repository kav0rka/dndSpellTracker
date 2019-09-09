package kavorka.dndspelltracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kavorka.dndspelltracker.data.PlayerCharacter

class MainViewModel: ViewModel() {
    private var playerCharacters: LiveData<List<PlayerCharacter>>? = null
    fun getCharacters() : LiveData<List<PlayerCharacter>> {
        if (playerCharacters == null) {
            playerCharacters = db.charactersDao().getAll()
        }
        return playerCharacters!!
    }
}