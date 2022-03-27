package com.example.simpletodo

import android.os.Bundle
import android.os.FileUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils.writeLines
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    //Create lists of tasks
    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Holds long click listener
        val onLongClickListener = object: TaskItemAdapter.OnLongClickListener {
            override fun onItemLongClicked(position: Int) {
                //1. Remove the item from the list
                listOfTasks.removeAt(position)
                //2. Notify adapter something has changed
                adapter.notifyDataSetChanged()

                saveItems()

            }

        }

        //Populate list of items
        loadItems()

        //1. Lets detect when user clicks on add button
//        findViewById<Button>(R.id.button).setOnClickListener{
//            // Code in here is going to be executed when user clicks (from on click listener)
//            Log.i("Caren","User clicked on button")
//        }

        //Creating a fake list
        //listOfTasks.add("Laundry")
        //listOfTasks.add("Walk")

        //Look up recyclerView in layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // Create adapter by passing in sample user data
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)
        // Attach adapter to recycler view
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up add button

        // Var to find by ID
        val inputTextField = findViewById<EditText>(R.id.addTaskField)
        // Grabbing a reference to button, setting on click listener
        findViewById<Button>(R.id.button).setOnClickListener {
            //Grab the text from @id/addTaskField (text id)
            val userInputtedTask = inputTextField.text.toString()
            //Add string to list of tasks (listOfTasks)
            listOfTasks.add(userInputtedTask)
            //Notify adapter that data has been added (won't show in app)
            adapter.notifyItemInserted(listOfTasks.size - 1)

            //Reset text field
            inputTextField.setText("")

            //Save item to file
            saveItems()

        }
    }

    //Save the data inputted by user
    //Saving data by writing/reading file

    //Create a method to get the file we need

    //Define function to get data file
    fun getDataFile(): File {

        // Every line is going to represent a task in list
        return File(filesDir, "data.txt")
    }

    // Load items by reading every line in date file
    fun loadItems() {
        try {
            listOfTasks = org.apache.commons.io.FileUtils.readLines(getDataFile(), Charset.defaultCharset())

        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }


    }

    // Save items by writing them into daa file
    fun saveItems() {
        try {
            org.apache.commons.io.FileUtils.writeLines(getDataFile(),listOfTasks)
        } catch(ioException: IOException){
            ioException.printStackTrace()
        }

    }


}
//Recycler view shows to handle items in list
//1. Define a model for recycler view