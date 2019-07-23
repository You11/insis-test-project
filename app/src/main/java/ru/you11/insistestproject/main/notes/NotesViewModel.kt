package ru.you11.insistestproject.main.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.you11.insistestproject.models.Color
import ru.you11.insistestproject.models.Note

class NotesViewModel: ViewModel() {

    val notes = MutableLiveData<ArrayList<Note>>()

    fun getInitialNotes() {
        val list = ArrayList<Note>()
        list.add(Note(id = 0, name = "Meow", description = "Meowmeowmeowmeow meowmeowmeowmeowmeowmeow. Meowmeowmeowmeowmeowmeow", color = Color.YELLOW))
        list.add(Note(id = 1, name = "Gav", description = "Gavgavgavgavgavgavgavgavgav gavgavgav"))
        list.add(Note(id = 2, name = "Normal note", description = "It's really not", color = Color.LIGHT_BLUE))
        list.add(Note(id = 3, name = "Meow", description = "Meowmeowmeowmeow meowmeowmeowmeowmeowmeow. Meowmeowmeowmeowmeowmeow", color = Color.YELLOW))
        list.add(Note(id = 4, name = "Gav", description = "Gavgavgavgavgavgavgavgavgav gavgavgav"))
        list.add(Note(id = 5, name = "Normal note", description = "It's really not", color = Color.LIGHT_BLUE))
        list.add(Note(id = 6, name = "Meow", description = "Meowmeowmeowmeow meowmeowmeowmeowmeowmeow. Meowmeowmeowmeowmeowmeow", color = Color.YELLOW))
        list.add(Note(id = 7, name = "Gav", description = "Gavgavgavgavgavgavgavgavgav gavgavgav"))
        list.add(Note(id = 8, name = "Normal note", description = "It's really not", color = Color.LIGHT_BLUE))
        list.add(Note(id = 9, name = "Meow", description = "Meowmeowmeowmeow meowmeowmeowmeowmeowmeow. Meowmeowmeowmeowmeowmeow", color = Color.YELLOW))

        list.sortBy { it.id }

        notes.postValue(list)
    }

    fun addNewNote(title: String, description: String) {
        val newNote = Note(id = getNewId(),
            name = title,
            description = description)

        val newNotes = getNotes()
        newNotes?.add(newNote)

        notes.postValue(newNotes)

    }

    private fun getNewId(): Int {
        val notes = getNotes() ?: return -1
        val latestId = notes[notes.size - 1].id
        return latestId + 1
    }

    private fun getNotes(): ArrayList<Note>? = notes.value
}