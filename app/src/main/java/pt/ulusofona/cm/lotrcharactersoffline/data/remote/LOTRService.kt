package pt.ulusofona.cm.lotrcharactersoffline.data.remote

import pt.ulusofona.cm.lotrcharactersoffline.LOTR_API_TOKEN
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

// only used for parsing JSON response
data class Character(val _id: String, val birth: String,
                     val death: String, val gender: String?, val name: String)

// only used for parsing JSON response
data class GetCharactersResponse(val docs: List<Character>, val total: Int)

interface LOTRService {

    @Headers("Authorization: Bearer ${LOTR_API_TOKEN}")
    @GET("character")
    suspend fun getCharacters(): GetCharactersResponse
}