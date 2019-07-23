package ru.you11.insistestproject.main.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.you11.insistestproject.models.Color
import ru.you11.insistestproject.models.Note

class NotesViewModel: ViewModel() {

    val notesData = MutableLiveData<ArrayList<Note>>()

    fun setInitialNotes() {
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

        notesData.postValue(list)
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