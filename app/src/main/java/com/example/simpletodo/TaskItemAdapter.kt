package com.example.simpletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter class
//A bridge that tells recycler view how to display data given

class TaskItemAdapter(val listOfItems: List<String>, val longClickListener: OnLongClickListener):
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    interface OnLongClickListener {
        fun onItemLongClicked(position: Int)
    }

    //Inflate layout from XML and return holder (tell recycler view how to layout each of its items)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        // Inflates each item
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout (open by command b)
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }
    //Populate layout of viewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get data model based on position
        val item = listOfItems.get(position)
        //Setting text view to whatever item is
        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    //Every item in list is an itemView
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Store references to elements in layout view
        val textView: TextView

        init {
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener {
                // Test it is working by printing log statement
                //Log.i("Caren", "Long clicked on item: " + adapterPosition)
                longClickListener.onItemLongClicked(adapterPosition)
                true

            }
        }

    }
}