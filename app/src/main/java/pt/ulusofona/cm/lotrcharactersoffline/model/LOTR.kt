package pt.ulusofona.cm.lotrcharactersoffline.model

abstract class LOTR {
    abstract fun getCharacters(onFinished: (List<LOTRCharacter>) -> Unit)
    abstract fun insertCharacters(characters: List<LOTRCharacter>, onFinished: () -> Unit)
    abstract fun clearAllCharacters(onFinished: () -> Unit)
}