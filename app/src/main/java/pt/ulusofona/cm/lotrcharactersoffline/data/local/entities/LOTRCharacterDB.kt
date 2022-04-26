package pt.ulusofona.cm.lotrcharactersoffline.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LOTRCharacterDB(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name="character_id")
    val characterId: String,  // characterId corresponds to LOTRCharacter.id
    val birth: String,
    val death: String,
    val gender: String?,  // gender is optional
    val name: String)
