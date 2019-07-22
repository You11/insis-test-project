package ru.you11.insistestproject.main.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.you11.insistestproject.R
import ru.you11.insistestproject.models.Note

class NotesRVAdapter(
    private val notes: List<Note>,
    private val listener: OnNoteClickListener
) : RecyclerView.Adapter<NotesRVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesRVViewHolder {
        return NotesRVViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: NotesRVViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size
}