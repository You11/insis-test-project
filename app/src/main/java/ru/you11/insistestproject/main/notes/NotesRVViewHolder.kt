package ru.you11.insistestproject.main.notes

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.you11.insistestproject.R
import ru.you11.insistestproject.models.Note

class NotesRVViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val layout: TextView = view.findViewById(R.id.note_layout)
    private val title: TextView = view.findViewById(R.id.note_title)
    private val description: TextView = view.findViewById(R.id.note_description)

    fun bind(item: Note) {
        title.text = item.name
        description.text = item.description
        layout.setBackgroundColor(Color.parseColor(item.color.hex))
    }
}