package com.example.pdevshali

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(val context: Context,
        val  noteClickDeleteInterface: NoteClickDeleteInterface,
        val noteClickInterface: NoteClickInterface,):
    RecyclerView.Adapter<NoteAdapter.ViewHolde>() {

        private val allNotes = ArrayList<Note>()
        inner class ViewHolde(itemView: View) : RecyclerView.ViewHolder(itemView){
            val noteTv = itemView.findViewById<TextView>(R.id.tvNoteTitle)
            val timeTv = itemView.findViewById<TextView>(R.id.tvTimestamp)
            val deleteTv = itemView.findViewById<ImageView>(R.id.ivDeleteBtn)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolde {
        // it is use to inflate view inside recycler view & create a new ViewHolder instance for the RecyclerView.

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item, parent, false)
        return ViewHolde(itemView)
    }

    override fun getItemCount(): Int {
       return allNotes.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolde, position: Int) {
       // Populating the data in View Holder
        holder.noteTv.setText(allNotes.get(position).noteTitle)
        holder.timeTv.setText("Last Updated: ${allNotes.get(position).timestamp}")

        holder.deleteTv.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(0, newList)
        notifyDataSetChanged()
    }
}


// It is for deleting the note from recycler view:

interface NoteClickDeleteInterface{
        fun onDeleteIconClick(note: Note)
}

// It is used to update the description of any note after clicking on that in Recycler view:

interface  NoteClickInterface{
    fun onNoteClick(note: Note)
}