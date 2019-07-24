package ru.you11.insistestproject.main.singlenote

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.you11.insistestproject.R
import ru.you11.insistestproject.main.BaseFragment

class SingleNoteFragment: BaseFragment<SingleNoteViewModel>() {

    private val args: SingleNoteFragmentArgs by navArgs()

    override fun createViewModel() = ViewModelProviders.of(this).get(SingleNoteViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_single_note, container, false)
        with(view) {
            findViewById<TextView>(R.id.single_note_title).apply {
                text = args.note?.name
            }

            findViewById<TextView>(R.id.single_note_description).apply {
                text = args.note?.description
            }

            findViewById<CardView>(R.id.single_note_card).apply {
                setViewBackgroundColor(this)
            }
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_single_note, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.single_note_menu_edit) {
            navigateToEditScreen()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setViewBackgroundColor(view: View) {
        val color = Color.parseColor(args.note?.color?.hex)
        if (view is CardView) {
            view.setCardBackgroundColor(color)
        } else {
            view.setBackgroundColor(color)
        }
    }

    private fun navigateToEditScreen() {
        val action = SingleNoteFragmentDirections.actionSingleNoteFragmentToAddNoteFragment(args.note)
        findNavController().navigate(action)
    }
}