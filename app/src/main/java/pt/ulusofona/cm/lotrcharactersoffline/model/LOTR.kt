package pt.ulusofona.cm.lotrcharactersoffline.model

abstract class LOTR {
    abstract fun getCharacters(onFinished: (List<LOTRCharacter>) -> Unit)
}