package com.rojer_ko.notes.presentation.ui.main

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rojer_ko.notes.R
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import java.util.regex.Pattern.matches

class MainActivityTest

@Test
fun check_detail_activity_intent_sent() {
    onView(withId(R.id.mainRecycler))
        .perform(actionOnItemAtPosition<MainAdapter.NoteViewHolder>(1, click()))
}

fun allOf(hasComponent: Matcher<Intent>?, hasExtra: Any): Matcher<Intent>? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

@Test
fun check_data_is_displayed() {
    onView(withId(R.id.mainRecycler))
        .perform(scrollToPosition<MainAdapter.NoteViewHolder>(1))
    val testNotes = null
}
