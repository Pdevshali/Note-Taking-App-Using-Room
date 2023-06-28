package com.example.pdevshali

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.Date

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var noteTitleEt : EditText
    lateinit var noteDescriptionEt : EditText
    lateinit var addUpdateBtn : Button
    lateinit var viewModel: NoteViewModel
    var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        noteTitleEt = findViewById(R.id.etNoteTitle)
        noteDescriptionEt = findViewById(R.id.etNoteDescription)
        addUpdateBtn = findViewById(R.id.btnAddUpdate)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteId = intent.getIntExtra("noteId", -1)
            addUpdateBtn.setText("Update Note")
            noteTitleEt.setText(noteTitle)
            noteDescriptionEt.setText(noteDesc)
        }else{
            addUpdateBtn.setText("Save Note")
        }

        addUpdateBtn.setOnClickListener {
            val noteTitle = noteTitleEt.text.toString()
            val noteDescription = noteDescriptionEt.text.toString()

            if (noteType == "Edit") {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val simpleDateFormat = SimpleDateFormat("dd MMM, yyy - HH:mm")
                    val currentDate: String = simpleDateFormat.format(Date())

                    val updateNote = Note(noteTitle, noteDescription, currentDate)
                    updateNote.id = noteId
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Please enter note title and description", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val simpleDateFormat = SimpleDateFormat("dd MMM, yyy - HH:mm")
                    val currentDate: String = simpleDateFormat.format(Date())

//                    val newNote = Note(noteTitle, noteDescription, currentDate)
//                    viewModel.allNotes.value?.let { existingNotes ->
//                        val updatedList = ArrayList(existingNotes) // Create a new list to avoid modifying the original list
//                        updatedList.add(0, newNote) // Add new note at index 0
//                        viewModel.updateNoteList(updatedList)

                    viewModel.addNote(Note(noteTitle, noteDescription, currentDate))
                    Toast.makeText(this, "Note Added", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Please enter note title and description", Toast.LENGTH_LONG).show()
                }
            }

        }


    }
}