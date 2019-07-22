package ru.you11.insistestproject.main.notes

import androidx.lifecycle.ViewModel
import ru.you11.insistestproject.models.Color
import ru.you11.insistestproject.models.Note

class NotesViewModel: ViewModel() {

    fun getInitialNotes(): List<Note> {

        val list = ArrayList<Note>()
        list.add(Note(name = "Meow", description = "Meowmeowmeowmeow meowmeowmeowmeowmeowmeow. Meowmeowmeowmeowmeowmeow", color = Color.YELLOW))
        list.add(Note(name = "Gav", description = "Gavgavgavgavgavgavgavgavgav gavgavgav"))
        list.add(Note(name = "Normal note", description = "It's really not", color = Color.LIGHT_BLUE))
        list.add(Note(name = "Meow", description = "Meowmeowmeowmeow meowmeowmeowmeowmeowmeow. Meowmeowmeowmeowmeowmeow", color = Color.YELLOW))
        list.add(Note(name = "Gav", description = "Gavgavgavgavgavgavgavgavgav gavgavgav"))
        list.add(Note(name = "Normal note", description = "It's really not", color = Color.LIGHT_BLUE))
        list.add(Note(name = "Meow", description = "Meowmeowmeowmeow meowmeowmeowmeowmeowmeow. Meowmeowmeowmeowmeowmeow", color = Color.YELLOW))
        list.add(Note(name = "Gav", description = "Gavgavgavgavgavgavgavgavgav gavgavgav"))
        list.add(Note(name = "Normal note", description = "It's really not", color = Color.LIGHT_BLUE))
        list.add(Note(name = "Meow", description = "Meowmeowmeowmeow meowmeowmeowmeowmeowmeow. Meowmeowmeowmeowmeowmeow", color = Color.YELLOW))
        list.add(Note(name = "Gav", description = "Gavgavgavgavgavgavgavgavgav gavgavgav"))
        list.add(Note(name = "Normal note", description = "It's really not", color = Color.LIGHT_BLUE))


        return list
    }
}