package com.rojer_ko.notes.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rojer_ko.notes.data.model.Color
import com.rojer_ko.notes.data.model.Note

object LocalRepository {
    private val notesLiveData = MutableLiveData<List<Note>>()
    val notes: MutableList<Note> = mutableListOf(
        Note(title =  "Заметка № 1",
            note = "Kotlin очень краткий, но при этом выразительный язык",
            color = Color.YELLOW),
        Note(title = "Заметка № 2",
            note = "Ключевое слово constructor обычно можно опустить",
            color = Color.BLUE),
        Note(title = "Заметка № 3",
            note = "В Java родителем всех классов является Object, а в Kotlin все классы наследуются от Any",
            color = Color.GREEN),
        Note(title = "Заметка № 4",
            note = "Чтобы класс был всегда в единственном экземпляре на протяжении работы приложения, достаточно слово class заменить на object",
            color = Color.RED),
        Note(title = "Заметка № 5",
            note = "Первым следует ключевое слово fun, далее — название функции с принимаемыми аргументами в скобках",
            color = Color.PINK),
        Note(title = "Заметка № 6",
            note = "В Kotlin, как и в Java, функции могут принимать нефиксированное количество параметров (varargs)",
            color = Color.VIOLET),
        Note(title = "Заметка № 7",
            note = "Объявление переменных в Kotlin отличается от Java. В начале объявления указывается ключевое слово var или val",
            color = Color.YELLOW),
        Note(title = "Заметка № 8",
            note = "В Kotlin не используется понятие «переменная класса», но есть термин «свойство класса»",
            color = Color.GREEN),
        Note(title = "Заметка № 9",
            note = "Создатели Kotlin рекомендуют использовать val, где это возможно",
            color = Color.RED),
        Note(title = "Заметка № 10",
            note = "В Android Studio можно посмотреть байт-код, в который компилируется ваш код, написанный на Kotlin",
            color = Color.BLUE)
    )

    init {
        notesLiveData.value = notes
    }

    fun getNotes(): LiveData<List<Note>> {
        return notesLiveData
    }

    fun saveNote(note: Note) {
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note) {

        for (i in 0 until notes.size) {
            if (notes[i] == note) {
                notes.set(i, note)
                return
            }
        }

        notes.add(note)
    }
}