package com.rojer_ko.notes.data.provider

import androidx.lifecycle.LiveData
import com.rojer_ko.notes.data.model.Note
import com.rojer_ko.notes.data.model.Result
import com.rojer_ko.notes.data.model.User

interface RemoteDataProvider {

    fun subscribeToAllNotes(): LiveData<Result>
    fun getNoteById(id: String): LiveData<Result>
    fun saveNote(note: Note) : LiveData<Result>
    fun getCurrentUser(): LiveData<User?>
    fun deleteNote(noteId: String): LiveData<Result>
}