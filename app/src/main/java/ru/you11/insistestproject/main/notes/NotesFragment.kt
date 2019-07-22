package ru.you11.insistestproject.main.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.you11.insistestproject.R

class NotesFragment : Fragment() {

    private lateinit var viewModel: NotesViewModel

    private lateinit var notesRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        with(view) {
            findViewById<FloatingActionButton>(R.id.notes_add_button).setOnClickListener {
                moveToAddNoteFragment(findNavController())
            }

            notesRV = findViewById(R.id.notes_rv)
            notesRV.adapter = NotesRVAdapter(viewModel.getInitialNotes())
            notesRV.layoutManager = LinearLayoutManager(activity)
        }

        return view
    }

    private fun createViewModel() = ViewModelProviders.of(this).get(NotesViewModel::class.java)

    private fun moveToAddNoteFragment(controller: NavController) {
        controller.navigate(R.id.action_notesFragment_to_addNoteFragment)
    }
}