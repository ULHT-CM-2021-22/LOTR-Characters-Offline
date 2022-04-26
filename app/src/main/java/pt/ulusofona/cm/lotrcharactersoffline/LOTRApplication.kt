package pt.ulusofona.cm.lotrcharactersoffline

import android.app.Application
import android.util.Log
import pt.ulusofona.cm.lotrcharactersoffline.data.LOTRRepository
import pt.ulusofona.cm.lotrcharactersoffline.data.local.LOTRDBWithRoom
import pt.ulusofona.cm.lotrcharactersoffline.data.local.LOTRDatabase
import pt.ulusofona.cm.lotrcharactersoffline.data.remote.LOTRServiceWithRetrofit
import pt.ulusofona.cm.lotrcharactersoffline.data.remote.RetrofitBuilder

class LOTRApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LOTRRepository.init(
            local = LOTRDBWithRoom(LOTRDatabase.getInstance(this).lotrOperations()),
            remote = LOTRServiceWithRetrofit(RetrofitBuilder.getInstance(LOTR_API_BASE_URL)),
            context = this
        )
        Log.i("APP", "Initialized repository")
    }
}