package pt.ulusofona.cm.lotrcharactersoffline.data.remote

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTR
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter
import retrofit2.HttpException
import retrofit2.Retrofit

class LOTRServiceWithRetrofit(val retrofit: Retrofit): LOTR() {

    override fun insertCharacters(characters: List<LOTRCharacter>, onFinished: () -> Unit) {
        throw Exception("Illegal operation")
    }

    override fun clearAllCharacters(onFinished: () -> Unit) {
        throw Exception("Illegal operation")
    }

    override fun getCharacters(onFinished: (List<LOTRCharacter>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = retrofit.create(LOTRService::class.java)

            try {
                val responseObj: GetCharactersResponse = service.getCharacters()
                onFinished(responseObj?.docs?.map {
                    LOTRCharacter(it._id, it.birth, it.death, it.gender.orEmpty(), it.name)
                })
            } catch (e: HttpException) {
                onFinished(emptyList())  // This should be handled with a onError() callback
            }
        }
    }
}