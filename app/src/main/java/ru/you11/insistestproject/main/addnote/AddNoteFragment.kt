package ru.you11.insistestproject.main.addnote

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import ru.you11.insistestproject.R
import ru.you11.insistestproject.main.BaseFragment
import ru.you11.insistestproject.main.MainActivity
import ru.you11.insistestproject.models.Note
import ru.you11.insistestproject.other.Consts

class AddNoteFragment : BaseFragment<AddNoteViewModel>() {

    private val args: AddNoteFragmentArgs by navArgs()

    private lateinit var noteTitle: EditText
    private lateinit var noteDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun createViewModel() = ViewModelProviders.of(this).get(AddNoteViewModel::class.java)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_note, container, false)
        with(view) {
            noteTitle = findViewById(R.id.add_note_title_edit)
            noteDescription = findViewById(R.id.add_note_description_edit)

            fillViews(args.note)
        }

        return view
    }

    private fun fillViews(note: Note?) {
        if (note == null) return
        noteTitle.setText(note.name)
        noteDescription.setText(note.description)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_note, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_note_menu_save) {
            saveNote()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun saveNote() {
        if (!isAllInputValid()) return

        val bundle = Bundle()
        val resultCode = setResultData(bundle)

        (activity as MainActivity).navigateBackWithResult(bundle, resultCode)
    }

    private fun isAllInputValid(): Boolean {
        val titleInput = noteTitle.text.toString()
        val descriptionInput = noteDescription.text.toString()

        if (!viewModel.isTitleInputValid(titleInput)) {
            showValidationError(noteTitle)
            return false
        }

        if (!viewModel.isDescriptionInputValid(descriptionInput)) {
            showValidationError(noteDescription)
            return false
        }

        return true
    }

    private fun showValidationError(view: EditText) {
        val invalidInputString = resources.getString(R.string.invalid_input)

        view.requestFocus()
        view.error = invalidInputString
    }

    private fun setResultData(bundle: Bundle): Int {
        val titleInput = noteTitle.text.toString()
        val descriptionInput = noteDescription.text.toString()

        val note = args.note
        return if (note == null) {
            bundle.putString("noteTitle", titleInput)
            bundle.putString("noteDescription", descriptionInput)
            Consts.ResultCodes.ADD_NOTE
        } else {
            val newNote = Note(
                id = note.id,
                name = titleInput,
                description = descriptionInput,
                color = note.color
            )
            bundle.putParcelable("note", newNote)
            Consts.ResultCodes.EDIT_NOTE
        }
    }
}