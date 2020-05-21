package com.rojer_ko.notes.presentation.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rojer_ko.notes.R
import com.rojer_ko.notes.data.model.Color
import com.rojer_ko.notes.data.model.Note
import com.rojer_ko.notes.presentation.extensions.getColorInt
import kotlinx.android.extensions.LayoutContainer

class MainAdapter(private val onItemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<MainAdapter.NoteViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])

    }

    inner class NoteViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val title = containerView.findViewById<TextView>(R.id.title)
        private val body = containerView.findViewById<TextView>(R.id.body)
        fun bind(note: Note) {
            title.text = note.title
            body.text = note.note
            itemView.setBackgroundColor(note.color.getColorInt(itemView.context))
            //itemView.setOnClickListener { onItemClickListener(note) }
            itemView.setOnClickListener { onItemClickListener.onItemClick(note) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }
}
