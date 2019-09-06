package kavorka.dndspelltracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharactersDao {
    @Query("select * from playercharacter")
    fun getAll(): List<PlayerCharacter>

    @Query("select * from playercharacter where name = :name")
    fun getCharacterByName(name: String) : PlayerCharacter

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(playerCharacter: PlayerCharacter)

    @Query("delete from playercharacter where name= :name")
    fun deleteCharacterByName(name: String)
}