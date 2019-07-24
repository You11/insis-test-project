package ru.you11.insistestproject.main.notes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.you11.insistestproject.R
import ru.you11.insistestproject.main.BaseFragment
import ru.you11.insistestproject.models.CardColor
import ru.you11.insistestproject.models.Note
import ru.you11.insistestproject.other.Consts
import ru.you11.insistestproject.other.JsonNoteGetter
import ru.you11.insistestproject.other.NavigationResult

class NotesFragment : BaseFragment<NotesViewModel>(), OnNoteClickListener, NavigationResult {

    private lateinit var notesRV: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        with(view) {
            findViewById<FloatingActionButton>(R.id.notes_add_button).setOnClickListener {
                moveToAddNoteFragment()
            }

            notesRV = findViewById(R.id.notes_rv)
            notesRV.adapter = NotesRVAdapter(this@NotesFragment)
            notesRV.layoutManager = LinearLayoutManager(activity)
        }

        return view
    }

    override fun createViewModel() = ViewModelProviders.of(this).get(NotesViewModel::class.java)

    override fun onResume() {
        super.onResume()

        viewModel.notesData.observe(this, Observer {
            (notesRV.adapter as NotesRVAdapter).updateAllNotes(it)
        })

        if (viewModel.getNotes().isNullOrEmpty()) {
            val jsonFileGetter = JsonNoteGetter(activity as Context)
            val data = jsonFileGetter.getJSONArrayListFromFile("data.json")
            viewModel.setInitialNotes(data)
        }
    }

    private fun moveToAddNoteFragment() {
        findNavController().navigate(R.id.action_notesFragment_to_addNoteFragment)
    }

    override fun onClick(item: Note) {
        val action = NotesFragmentDirections.actionNotesFragmentToSingleNoteFragment(note = item)
        findNavController().navigate(action)
    }

    override fun onNavigationResult(result: Bundle, resultCode: Int) {
        when (resultCode) {
            Consts.ResultCodes.ADD_NOTE -> {
                viewModel.addNewNote(
                    title = result.getString("noteTitle") ?: "",
                    description = result.getString("noteDescription") ?: "",
                    color = result.get("noteColor") as CardColor? ?: CardColor.WHITE
                )
            }

            Consts.ResultCodes.EDIT_NOTE -> {
                viewModel.changeNote(result.getParcelable("note"))
            }

            else -> return
        }
    }
}