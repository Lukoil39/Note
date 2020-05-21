package com.rojer_ko.notes.presentation.ui.note

import com.rojer_ko.notes.data.model.Note
import com.rojer_ko.notes.presentation.ui.base.BaseViewState

class NoteViewState(data: Data = Data(),
                    error: Throwable? = null) : BaseViewState<NoteViewState.Data>(data, error) {

    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}
