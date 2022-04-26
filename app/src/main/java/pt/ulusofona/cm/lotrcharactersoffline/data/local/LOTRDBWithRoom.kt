package pt.ulusofona.cm.lotrcharactersoffline.data.local

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.lotrcharactersoffline.data.local.dao.LOTROperations
import pt.ulusofona.cm.lotrcharactersoffline.data.local.entities.LOTRCharacterDB
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTR
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter

class LOTRDBWithRoom(private val storage: LOTROperations): LOTR() {

    override fun insertCharacters(characters: List<LOTRCharacter>, onFinished: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            characters.map {
                LOTRCharacterDB(
                    characterId = it.id,
                    birth = it.birth,
                    death = it.death,
                    gender = it.gender,
                    name = it.name
                )
            }.forEach {
                storage.insert(it)
                Log.i("APP", "Inserted ${it.name} in DB")
            }
            onFinished()  // TODO
        }

    }

    override fun getCharacters(onFinished: (List<LOTRCharacter>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val characters = storage.getAll().map {
                LOTRCharacter(
                    id = it.characterId,
                    birth = it.birth,
                    death = it.death,
                    gender = it.gender,
                    name = it.name
                )
            }
            onFinished(characters)
        }
    }
}