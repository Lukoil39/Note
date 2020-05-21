package com.rojer_ko.notes.presentation.ui.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.rojer_ko.notes.data.model.Note
import com.rojer_ko.notes.data.model.Result
import com.rojer_ko.notes.data.model.Result.*
import com.rojer_ko.notes.data.repository.Repository
import com.rojer_ko.notes.presentation.ui.base.BaseViewModel

class MainViewModel(val repository: Repository) : BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = object : Observer<Result> {//Стандартный обсервер LiveData
    override fun onChanged(t: Result?) {
        if (t == null) return

        when(t) {
            is Success<*> -> {
// Может понадобиться вручную импортировать класс data.model.NoteResult.Success
                viewStateLiveData.value = MainViewState(notes = t.data as? List<Note>)
            }
            is Error -> {
// Может понадобиться вручную импортировать класс data.model.NoteResult.Error
                viewStateLiveData.value = MainViewState(error = t.error)
            }
        }
    }
    }

    private val repositoryNotes = repository.getNotes()

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)
    }

    @VisibleForTesting
    public override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
    }
}
