package pt.ulusofona.cm.lotrcharactersoffline

import android.app.Application
import android.util.Log
import okhttp3.OkHttpClient
import pt.ulusofona.cm.lotrcharactersoffline.data.LOTRRepository
import pt.ulusofona.cm.lotrcharactersoffline.data.local.LOTRDBWithRoom
import pt.ulusofona.cm.lotrcharactersoffline.data.local.LOTRDatabase
import pt.ulusofona.cm.lotrcharactersoffline.data.remote.LOTRServiceWithOkHttpAndJSONObject

class LOTRApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LOTRRepository.init(
            local = LOTRDBWithRoom(LOTRDatabase.getInstance(this).lotrOperations()),
            remote = LOTRServiceWithOkHttpAndJSONObject(client = OkHttpClient()),
            context = this
        )
        Log.i("APP", "Initialized repository")
    }
}

