package ru.you11.insistestproject.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.you11.insistestproject.R

class NotesFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        with(view) {
            findViewById<FloatingActionButton>(R.id.notes_add_button).setOnClickListener {
                findNavController().navigate(R.id.action_notesFragment_to_addNoteFragment)
            }
        }

        return view
    }
}