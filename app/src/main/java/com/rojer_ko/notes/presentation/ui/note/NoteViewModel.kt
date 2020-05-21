package com.rojer_ko.notes.presentation.ui.note
import com.rojer_ko.notes.data.model.Note
import com.rojer_ko.notes.data.model.Result.*
import com.rojer_ko.notes.data.repository.Repository
import com.rojer_ko.notes.presentation.ui.base.BaseViewModel

class NoteViewModel(private val repository: Repository) : BaseViewModel<NoteViewState.Data,
        NoteViewState>() {

    private val currentNote: Note?
        get() = viewStateLiveData.value?.data?.note

    fun saveChanges(note: Note) {
        viewStateLiveData.value = NoteViewState(NoteViewState.Data(note = note))
    }

    private val viewModel: NoteViewModel = spyk(NoteViewModel(MockK()))

    private fun MockK(): Repository {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun spyk(noteViewModel: NoteViewModel): NoteViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCleared() {
        currentNote?.let { repository.saveNote(it) }
    }

    fun loadNote(noteId: String) {
        repository.getNoteById(noteId).observeForever { t ->
            t?.let {
                viewStateLiveData.value = when (t) {
                    is Success<*> -> NoteViewState(NoteViewState.Data(note = t.data as? Note))
                    is Error -> NoteViewState(error = t.error)
                }
            }
        }
    }

    fun deleteNote() {
        currentNote?.let { it ->
            repository.deleteNote(it.id).observeForever { t ->
                t?.let {
                    viewStateLiveData.value = when (it) {
                        is Success<*> -> NoteViewState(NoteViewState.Data(isDeleted = true))
                        is Error -> NoteViewState(error = it.error)
                    }
                }
            }
        }
    }
}
