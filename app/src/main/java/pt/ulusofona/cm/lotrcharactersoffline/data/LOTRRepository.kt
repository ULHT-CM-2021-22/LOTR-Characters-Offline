package pt.ulusofona.cm.lotrcharactersoffline.data

import android.content.Context
import android.util.Log
import pt.ulusofona.cm.lotrcharactersoffline.data.remote.ConnectivityUtil
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTR
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter
import java.lang.IllegalStateException

class LOTRRepository private constructor(val local: LOTR, val remote: LOTR, val context: Context): LOTR() {

    override fun getCharacters(onFinished: (List<LOTRCharacter>) -> Unit) {
        if (ConnectivityUtil.isOnline(context)) {
            Log.i("APP", "App is online. Getting characters from the server...")
            remote.getCharacters { characters ->
                Log.i("APP", "Got ${characters.size} characters from the server")
                local.clearAllCharacters {
                    Log.i("APP", "Cleared DB")
                    local.insertCharacters(characters) {
                        onFinished(characters)
                    }
                }
            }
        } else {
            Log.i("APP", "App is offline. Getting characters from the database...")
            local.getCharacters(onFinished)
        }
    }

    override fun insertCharacters(characters: List<LOTRCharacter>, onFinished: () -> Unit) {
        throw Exception("Illegal operation")
    }

    override fun clearAllCharacters(onFinished: () -> Unit) {
        throw Exception("Illegal operation")
    }

    companion object {
        private var instance: LOTRRepository? = null

        fun init(local: LOTR, remote: LOTR, context: Context) {
            synchronized(this) {
                if (instance == null) {
                    instance = LOTRRepository(local, remote, context)
                }
            }
        }

        fun getInstance(): LOTRRepository {
            if (instance == null) {
                throw IllegalStateException("singleton not initialized")
            }
            return instance as LOTRRepository

        }
    }
}