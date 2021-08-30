package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

// Manifest files,declare application settings such as the icon our app launches with
// Manifest files hold the name of the app, theme, activity, including permission to access the internet
// res folder = resources, we have additional folders:
// gradle = build engine of android, has the job to take all the files and combine them together to make an executable app.
//build.gradle/Module.app = dependencies = links to third party libraries that we want to include in our project
// the particular view is not default in android studio, we need to add a dependency for that so it can be included-     implementation 'com.google.android.material:material:1.3.0-alpha02'


// #1 build layout of app/ activity_main
// -- drag and drop recyclerview on constraint layout, set constraints and id's
// -- A recyclerView is a list of items.. has a recycling behaviour, it will only show the items that are visible to the user or close to be visible.
// -- We want the recyclerview to take most of the view, so set the height to 0dp
// -- make sure to constraint the recycler view to the buttons, and then set to 0dp to take up the whole space.

// #2 views we need in app
// -- editText, constraints and id's
// -- Add hint: android:hint="Enter what you want to write"

// #3 Add Floating buttons
// -- add floating button to add and constraints
// -- add floating button to remove  and constraint

//#4  Adding Check box
// -- Another layout, need to specify a layout file for each single item on the recyclerview, so how these items will look like for example -- a checkbox and text, it needs to be defined
// -- go under layout folder, create a new layout file called item_todo
// -- this will create a new view ... so add textview
// -- we want to have the constraint layout to have a limit and not the whole screen, so give it a height of like 80dp., that way it shows at the top.
//

//. date, calender, order of importance(priority) , way of organizing in a simple way


class MainActivity : AppCompatActivity() {
    // Activity = single screen in our app that will hold several views
    // main activity inherits from AppcompatActivity
    private lateinit var todoAdapter: TodoAdapter// private lateinit tells android that we will intialize it later, the to_do adapter is of type To_do adpater, of the class we created

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)// Set the design of the activity, we set the contentView to the layout file XML

        //RecyclerView adapter is a class where we will define the layout we want to use for those recyclerview items(to_do.xml)
        // and a list that populate our recyclerview with data and define the logic, which views with which data
        //  #1 create a new to_do Kotlin Class under the todolist package, it will be a dataclass
        // The primary purpose of a data class is to hold data
        // #2 After creating the dataClass to do, now we can create an adapter
        //

        todoAdapter = TodoAdapter(mutableListOf())// empty mutableList can be changed

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)
        //rvTodoItems.layoutManager = GridLayoutManager(this, 3)

        fabAdd.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
       fabDelete.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}