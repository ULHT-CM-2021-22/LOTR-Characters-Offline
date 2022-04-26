package pt.ulusofona.cm.lotrcharacters.ui.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import pt.ulusofona.cm.lotrcharactersoffline.data.remote.LOTRServiceWithRetrofit
import pt.ulusofona.cm.lotrcharactersoffline.data.remote.RetrofitBuilder
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTR
import pt.ulusofona.cm.lotrcharactersoffline.LOTR_API_BASE_URL
import pt.ulusofona.cm.lotrcharactersoffline.data.LOTRRepository
import pt.ulusofona.cm.lotrcharactersoffline.data.local.LOTRDBWithRoom
import pt.ulusofona.cm.lotrcharactersoffline.data.local.LOTRDatabase
import pt.ulusofona.cm.lotrcharactersoffline.ui.viewModels.CharacterUI

class LOTRViewModel(application: Application): AndroidViewModel(application) {

    private val model: LOTR = LOTRRepository.getInstance()

    fun getCharacters(onFinished: (ArrayList<CharacterUI>) -> Unit) {

        // transforms "pure" LOTRCharacters into parcelable UICharacters
        model.getCharacters {
            Log.i("APP", "Received ${it.size} characters from WS")
            val charactersUI = ArrayList(it.map { character ->
                CharacterUI(
                    character.id,
                    character.birth,
                    character.death,
                    character.gender,
                    character.name
                )
            })
            onFinished(charactersUI)
        }
    }
}