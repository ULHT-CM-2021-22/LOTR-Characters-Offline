package pt.ulusofona.cm.lotrcharactersoffline.ui.viewModels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterUI(val _id: String,
                       val birth: String,
                       val death: String,
                       val gender: String?,
                       val name: String) : Parcelable
