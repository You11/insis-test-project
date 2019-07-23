package ru.you11.insistestproject.main.addnote

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.you11.insistestproject.R
import ru.you11.insistestproject.main.MainActivity
import ru.you11.insistestproject.models.Note
import ru.you11.insistestproject.other.Consts

class AddNoteFragment: Fragment() {

    private lateinit var noteTitle: EditText
    private lateinit var noteDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_add_note, container, false)
        with(view) {
            noteTitle = findViewById(R.id.add_note_title_edit)
            noteDescription = findViewById(R.id.add_note_description_edit)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_note_menu_save) {
            saveNote()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun saveNote() {
        val note = Note(name = noteTitle.text.toString(),
            description = noteDescription.text.toString())

        val bundle = Bundle()
        bundle.putParcelable("note", note)

        (activity as MainActivity).navigateBackWithResult(bundle, Consts.ResultCodes.ADD_NOTE)
    }
}