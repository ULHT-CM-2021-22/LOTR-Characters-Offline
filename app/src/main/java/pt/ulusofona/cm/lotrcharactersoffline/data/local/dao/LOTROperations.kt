package pt.ulusofona.cm.lotrcharactersoffline.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.ulusofona.cm.lotrcharactersoffline.data.local.entities.LOTRCharacterDB

@Dao
interface LOTROperations {

    @Insert
    suspend fun insert(character: LOTRCharacterDB)

    @Query("SELECT * FROM lotrcharacterdb")
    suspend fun getAll(): List<LOTRCharacterDB>

    @Query("SELECT * FROM lotrcharacterdb WHERE id = :id")
    suspend fun getById(id: Int): LOTRCharacterDB

    @Query("DELETE FROM lotrcharacterdb")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<LOTRCharacterDB>)
}
