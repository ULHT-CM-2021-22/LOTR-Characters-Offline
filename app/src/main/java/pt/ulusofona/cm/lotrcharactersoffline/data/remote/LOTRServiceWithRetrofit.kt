package pt.ulusofona.cm.lotrcharactersoffline.data.remote

import pt.ulusofona.cm.lotrcharactersoffline.model.LOTR
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter
import retrofit2.Retrofit

class LOTRServiceWithRetrofit(val retrofit: Retrofit): LOTR() {

    override fun insertCharacters(characters: List<LOTRCharacter>, onFinished: () -> Unit) {
        throw Exception("Illegal operation")
    }

    override fun getCharacters(onFinished: (List<LOTRCharacter>) -> Unit) {

        val service = retrofit.create(LOTRService::class.java)

        val response = service.getCharacters().execute()
        if (response.isSuccessful) {
            val responseObj = response.body()
            onFinished(responseObj?.docs?.map {
                LOTRCharacter(it._id, it.birth, it.death, it.gender.orEmpty(), it.name)
            }.orEmpty())
        } else {
            onFinished(emptyList())  // This should be handled with a onError() callback
        }
    }
}