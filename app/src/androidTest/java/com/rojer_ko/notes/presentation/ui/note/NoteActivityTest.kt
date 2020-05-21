package com.rojer_ko.notes.presentation.ui.note

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import com.google.common.base.CharMatcher
import com.google.common.base.CharMatcher.`is`
import com.rojer_ko.notes.R
import io.mockk.*
import junit.framework.Assert.assertTrue
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import java.util.regex.Pattern.matches

class NoteActivityTest

private val Nothing?.id: Bundle?
    get() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
@get:Rule
val activityTestRule = ActivityTestRule(NoteActivity::class.java, true, false)

private val viewModel: NoteViewModel = spyk(NoteViewModel(mockk()))

@Before
fun setUp() {
    loadKoinModules(
        listOf(
            module {
                viewModel() { viewModel }
            })
    )

    every { viewModel.getViewState() } returns viewStateLiveData
    every { viewModel.loadNote(any()) } just runs
    every { viewModel.saveChanges(any()) } just runs
    every { viewModel.deleteNote() } just runs

    Intent().apply {
        val testNote = null
        putExtra(NoteActivity::class.java.name + "extra.NOTE_ID", testNote.id)
    }.let {
        activityTestRule.launchActivity(it)
    }

    fun matches(completelyDisplayed: Matcher<View>?): ViewAssertion? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    fun should_show_color_picker() {
        onView(withId(R.id.palette)).perform(click())

        onView(withId(R.id.colorPicker)).check(matches(isCompletelyDisplayed()))
    }

    fun withTagValue(charMatcher: CharMatcher?): Matcher<View>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    fun should_set_toolbar_color() {
        onView(withId(R.id.palette)).perform(click())
        onView(withTagValue(`is`(Color.BLUE.toChar()))).perform(click())

        val colorInt = Color.BLUE.getColorInt(activityTestRule.activity)

        onView(withId(R.id.toolbar)).check { view, _ ->
            assertTrue("toolbar background color does not match",
                (view.background as? ColorDrawable)?.color == colorInt)
        }
    }
}

private fun Int.getColorInt(activity: Any): Any? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}


