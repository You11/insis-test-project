package ru.you11.insistestproject.main.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import ru.you11.insistestproject.models.Color
import ru.you11.insistestproject.models.Note

class NotesViewModel: ViewModel() {

    val notesData = MutableLiveData<ArrayList<Note>>()

    fun setInitialNotes(data: ArrayList<Note>) {
        data.sortBy { it.id }
        notesData.postValue(data)
    }

    fun getNotes(): ArrayList<Note>? = notesData.value

    fun addNewNote(title: String, description: String) {
        val newNote = Note(id = getNewId(),
            name = title,
            description = description)

        val newNotes = getNotes()
        newNotes?.add(newNote)

        notesData.postValue(newNotes)
    }

    fun changeNote(newNote: Note) {
        val notes = getNotes() ?: return
        val oldNote = notes.find { newNote.id == it.id }
        val notePosition = notes.indexOf(oldNote)
        notes.remove(oldNote)
        notes.add(notePosition, newNote)

        notesData.postValue(notes)
    }

    private fun getNewId(): Int {
        val notes = getNotes() ?: return -1
        val latestId = notes[notes.size - 1].id
        return latestId + 1
    }
}