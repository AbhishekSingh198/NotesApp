package com.example.notesapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.ActivityCreateNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class CreateNote : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNoteBinding
    var currentDate:String?=null
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.colorView.setBackgroundColor(Color.parseColor(ColourPicker.getColor()))
        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        currentDate=SimpleDateFormat(" dd/MM/yyyy, hh:mm:ss").format(Date())
        binding.dateAndTime.text="DATE AND TIME :"+currentDate
    }
    fun submitData(view: View) {
        val noteText=binding.inputNote.text.toString()
        val noteTitle= binding.inputTitle.text.toString()
        val noteSubtitle= binding.inputSubTitle.text.toString()
        if (noteText.isNotEmpty()||noteTitle.isNotEmpty()||noteSubtitle.isNotEmpty()){
            viewModel.insertNote(Note(noteTitle,noteSubtitle,currentDate,noteText))
            Toast.makeText(this,"$noteTitle Saved", Toast.LENGTH_SHORT).show()
        }
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun back(view: View) {
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}