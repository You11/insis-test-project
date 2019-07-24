package ru.you11.insistestproject.main.addnote

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import ru.you11.insistestproject.R
import ru.you11.insistestproject.main.BaseFragment
import ru.you11.insistestproject.main.MainActivity
import ru.you11.insistestproject.models.CardColor
import ru.you11.insistestproject.models.Note
import ru.you11.insistestproject.other.Consts

class AddNoteFragment : BaseFragment<AddNoteViewModel>() {

    private val args: AddNoteFragmentArgs by navArgs()

    private lateinit var noteTitle: EditText
    private lateinit var noteDescription: EditText
    private lateinit var noteRoot: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun createViewModel() = ViewModelProviders.of(this).get(AddNoteViewModel::class.java)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_add_note, container, false)
        with(root) {
            noteRoot = root
            noteTitle = findViewById(R.id.add_note_title_edit)
            noteDescription = findViewById(R.id.add_note_description_edit)

            fillViews(args.note)
            setupCircles(root)
        }

        return root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("color", viewModel.getCardColor())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        viewModel.setCardColor(savedInstanceState?.getString("color") ?: args.note?.color?.hex ?: CardColor.WHITE.hex)
    }

    override fun onResume() {
        super.onResume()

        viewModel.cardColor.observe(this, Observer {
            noteRoot.setBackgroundColor(Color.parseColor(it))
        })
    }

    private fun setupCircles(root: View) {
        val circles = hashMapOf<View, String>()
        circles[root.findViewById(R.id.firstCircle)] = CardColor.WHITE.hex
        circles[root.findViewById(R.id.secondCircle)] = CardColor.RED.hex
        circles[root.findViewById(R.id.thirdCircle)] = CardColor.ORANGE.hex
        circles[root.findViewById(R.id.fourthCircle)] = CardColor.YELLOW.hex
        circles[root.findViewById(R.id.fifthCircle)] = CardColor.GREEN.hex
        circles[root.findViewById(R.id.sixthCircle)] = CardColor.LIGHT_BLUE.hex
        circles[root.findViewById(R.id.seventhCircle)] = CardColor.BLUE.hex
        circles[root.findViewById(R.id.eighthCircle)] = CardColor.PURPLE.hex


        for ((view, colorHex) in circles) {
            (view.background as GradientDrawable).setColor(Color.parseColor(colorHex))
            view.setOnClickListener {
                viewModel.setCardColor(colorHex)
            }
        }
    }

    private fun fillViews(note: Note?) {
        if (note == null) return
        noteTitle.setText(note.name)
        noteDescription.setText(note.description)
        viewModel.cardColor.postValue(note.color.hex)
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

        hideKeyboard()

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

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun setResultData(bundle: Bundle): Int {
        val titleInput = noteTitle.text.toString()
        val descriptionInput = noteDescription.text.toString()

        val note = args.note
        val color = CardColor.values().find { it.hex == viewModel.getCardColor() }
        return if (note == null) {
            bundle.putString("noteTitle", titleInput)
            bundle.putString("noteDescription", descriptionInput)
            bundle.putSerializable("noteColor", color ?: CardColor.WHITE.hex)
            Consts.ResultCodes.ADD_NOTE
        } else {
            val newNote = Note(
                id = note.id,
                name = titleInput,
                description = descriptionInput,
                color = color ?: note.color
            )
            bundle.putParcelable("note", newNote)
            Consts.ResultCodes.EDIT_NOTE
        }
    }
}