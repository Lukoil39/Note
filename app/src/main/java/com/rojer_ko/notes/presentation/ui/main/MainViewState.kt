package com.rojer_ko.notes.presentation.ui.main

import com.rojer_ko.notes.data.model.Note
import com.rojer_ko.notes.presentation.ui.base.BaseViewState

//data class MainViewState(notes: List<Note>? = null, error: Throwable? = null)
//    : BaseViewState<List<Note>?>(notes, error)

class MainViewState(notes: List<Note>? = null, error: Throwable? = null)
    : BaseViewState<List<Note>?>(notes, error)