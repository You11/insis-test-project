package ru.you11.insistestproject.main.notes

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.you11.insistestproject.R
import ru.you11.insistestproject.models.Note

class NotesRVViewHolder(view: View, listener: OnNoteClickListener): RecyclerView.ViewHolder(view) {

    private val layout: View = view.findViewById(R.id.note_card)
    private val title: TextView = view.findViewById(R.id.note_title)
    private val description: TextView = view.findViewById(R.id.note_description)

    private lateinit var item: Note

    init {
        layout.setOnClickListener {
            listener.onClick(item)
        }
    }

    fun bind(item: Note) {
        this.item = item

        title.text = item.name
        description.text = item.description
        setColor()
    }

    private fun setColor() {
        val color = Color.parseColor(item.color.hex)
        if (layout is CardView) {
            layout.setCardBackgroundColor(color)
        } else {
            layout.setBackgroundColor(color)
        }
    }
}

interface OnNoteClickListener {

    fun onClick(item: Note)
}