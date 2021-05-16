package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.recyclerView.layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        val adapter=NoteRVAdapter(this,this)
        binding.recyclerView.adapter=adapter
        viewModel=ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let{
                adapter.updateList(it)
            }

        })
        binding.addNote.setOnClickListener {
            val intent= Intent(this,CreateNote::class.java)
            startActivity(intent)
        }

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_LONG).show()
    }


}