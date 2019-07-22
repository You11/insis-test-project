package ru.you11.insistestproject.main.singlenote

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import ru.you11.insistestproject.R

class SingleNoteFragment: Fragment() {

    private val args: SingleNoteFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_single_note, container, false)
        with(view) {
            findViewById<TextView>(R.id.single_note_title).apply {
                text = args.note?.name
            }

            findViewById<TextView>(R.id.single_note_description).apply {
                text = args.note?.description
            }

            findViewById<View>(R.id.single_note_layout).apply {
                setBackgroundColor(Color.parseColor(args.note?.color?.hex))
            }
        }

        return view
    }
}