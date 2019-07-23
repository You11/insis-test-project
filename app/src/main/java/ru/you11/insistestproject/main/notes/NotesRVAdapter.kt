package ru.you11.insistestproject.main.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.you11.insistestproject.R
import ru.you11.insistestproject.models.Note

class NotesRVAdapter(private val listener: OnNoteClickListener) : RecyclerView.Adapter<NotesRVViewHolder>() {

    private val notes = ArrayList<Note>()

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

    fun updateAllNotes(newNotes: ArrayList<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    fun addNote(newNote: Note) {
        notes.add(newNote)
        notifyItemInserted(notes.size - 1)
    }

    fun updateNote(newNote: Note) {
        val oldNote = notes.find { newNote.id == it.id }
        val position = notes.indexOf(oldNote)
        notes.remove(oldNote)
        notes.add(position, newNote)
        notifyItemChanged(position)
    }
}