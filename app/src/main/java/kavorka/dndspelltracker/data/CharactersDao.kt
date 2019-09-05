package kavorka.dndspelltracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharactersDao {
    @Query("select * from character")
    fun getAll(): List<Character>

    @Query("select * from character where name = :name")
    fun getCharacterByName(name: String) : Character

    @Insert
    fun insert(character: Character)

    @Query("delete from character where name= :name")
    fun deleteCharacterByName(name: String)
}