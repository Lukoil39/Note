package com.rojer_ko.notes.presentation.ui.common

import android.text.TextWatcher

interface MyTextWatcher: TextWatcher  {

override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // not used
        }

override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // not used
        }

}