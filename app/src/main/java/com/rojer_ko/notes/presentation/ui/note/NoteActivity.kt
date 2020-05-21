package com.rojer_ko.notes.presentation.ui.note

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.rojer_ko.notes.R
import com.rojer_ko.notes.data.model.Color
import com.rojer_ko.notes.data.model.Note
import com.rojer_ko.notes.presentation.extensions.SAVE_DELAY
import com.rojer_ko.notes.presentation.extensions.format
import com.rojer_ko.notes.presentation.extensions.getColorInt
import com.rojer_ko.notes.presentation.ui.base.BaseActivity
import com.rojer_ko.notes.presentation.ui.common.MyTextWatcher
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


@SuppressLint("ParcelCreator")
class NoteActivity() : BaseActivity<NoteViewState.Data, NoteViewState>(), Parcelable {

    companion object {
        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"
        fun getStartIntent(context: Context, noteId: String?): Intent {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, noteId)
            return intent
        }
    }

    //override val viewModel: com.rojer_ko.notes.presentation.ui.note.NoteViewModel by lazy { ViewModelProviders.of(this).get(com.rojer_ko.notes.presentation.ui.note.NoteViewModel::class.java) }
    override val model: NoteViewModel by viewModel()
    override val layoutRes: Int = R.layout.activity_note
    private var note: Note? = null
    private var noteId: String? = null
    private lateinit var color: Color

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        noteId = intent.getStringExtra(EXTRA_NOTE)
        noteId?.let {
            model.loadNote(it)
        }
        initActionBar()
        titleEt.addTextChangedListener(textChangeListener)
        bodyEt.addTextChangedListener(textChangeListener)

        colorPicker.onColorClickListener = {
            color = it
            setToolbarColor(it)
            triggerSaveNote()
        }

    }

    override fun renderData(data: NoteViewState.Data) {
        if (data.isDeleted) finish()

        this.note = data.note
        data.note?.let { color = it.color }
        initView()
    }

    private fun initActionBar(){
        setSupportActionBar(note_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (noteId == null ) supportActionBar?.title = getString(R.string.new_note_title)
    }

    private fun initView() {

        note?.run {
            supportActionBar?.title = lastChanged.format()
            supportActionBar?.setBackgroundDrawable(ColorDrawable(color.getColorInt(this@NoteActivity)))

            removeEditListener()
            titleEt.setText(this.title)
            bodyEt.setText(this.note)
            setEditListener()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
        menuInflater.inflate(R.menu.note_menu, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> super.onBackPressed().let { true }
        R.id.palette -> togglePalette().let { true }
        R.id.delete -> deleteNote(this.appbar).let { true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setEditListener() {
        titleEt.addTextChangedListener(textChangeListener)
        bodyEt.addTextChangedListener(textChangeListener)
    }

    private fun removeEditListener() {
        titleEt.removeTextChangedListener(textChangeListener)
        bodyEt.removeTextChangedListener(textChangeListener)
    }

    private fun deleteNote(view: View) {

        Snackbar.make(view, "Do you want to delete this Note", Snackbar.LENGTH_LONG)
            .setAction("OK") {model.deleteNote()}.show()
    }

    private val textChangeListener = object : MyTextWatcher {
        override fun afterTextChanged(s: Editable?) {
            triggerSaveNote()
        }
    }

    constructor(parcel: Parcel) : this() {
        note = parcel.readParcelable(Note::class.java.classLoader)
        noteId = parcel.readString()
    }

    private fun createNewNote(): Note = Note(
            title = titleEt.text.toString(),
            note = bodyEt.text.toString()
        )

    private fun triggerSaveNote() {
        if (titleEt.text!!.length < 3 && bodyEt.text.length < 3) return

        Handler().postDelayed({
            note = note?.copy(title = titleEt.text.toString(),
                note = bodyEt.text.toString(),
                lastChanged = Date(),
                color = color)
                ?: createNewNote()

            note?.let { model.saveChanges(it) }
        }, SAVE_DELAY)
    }

    private fun setToolbarColor(color: Color) {
        supportActionBar?.setBackgroundDrawable(ColorDrawable(color.getColorInt(this@NoteActivity)))
    }

    private fun togglePalette() {
        if (colorPicker.isOpen) {
            colorPicker.close()
        } else {
            colorPicker.open()
        }
    }

    override fun onBackPressed() {
        if (colorPicker.isOpen) {
            colorPicker.close()
            return
        }
        super.onBackPressed()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(note, flags)
        parcel.writeString(noteId)
    }

    override fun describeContents(): Int {
        return 0
    }

    private object CREATOR : Parcelable.Creator<NoteActivity> {
        override fun createFromParcel(parcel: Parcel): NoteActivity {
            return NoteActivity(parcel)
        }

        override fun newArray(size: Int): Array<NoteActivity?> {
            return arrayOfNulls(size)
        }
    }
}


