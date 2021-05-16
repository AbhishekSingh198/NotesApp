package com.example.notesapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(private val context: Context, private val listener:INotesRVAdapter): RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {
    private val allNotes= ArrayList<Note>()

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title: TextView =itemView.findViewById(R.id.text)
        val delete: ImageView =itemView.findViewById(R.id.deleteButton)
        val share: ImageView =itemView.findViewById(R.id.shareButton)
        val subTitle: TextView =itemView.findViewById(R.id.subText)
        val description: TextView =itemView.findViewById(R.id.desText)
        val dateAndTime: TextView =itemView.findViewById(R.id.dateAndTime)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.delete.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return  viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=allNotes[position]
        holder.title.text=currentNote.text
        holder.subTitle.text=currentNote.subTitle
        holder.description.text=currentNote.noteText
        holder.dateAndTime.text=currentNote.dateTime

        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColourPicker.getColor()))
        holder.share.setOnClickListener {
            val intent= Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"NOTE TITLE : "+allNotes[position].text+"\n"+"NOTE DESCRIPTION :\n"+allNotes[position].noteText)
            val chooser= Intent.createChooser(intent,"Share this note text using...")
            startActivity(context,chooser,null)

        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface INotesRVAdapter{
    fun onItemClicked(note: Note)
}