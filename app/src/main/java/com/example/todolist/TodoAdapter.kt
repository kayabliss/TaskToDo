package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

// This class will contain the main logic, the Recyclerview.adapter will populate the data into the Recyclerview
// The adapter's Role is to convert an object at a position into a list row item to be inserted



//1. Create the basic adapter extending from RecyclerView
//2. in the RecyclerView extension, we specify the custom view holder which gives us access to our views

class TodoAdapter(private val todos: MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
// this private variable/value to_dos can only be accessed by this todoadapter Class
// In the constructor specify a mutable list called" todos" the  mutable list is of type "To_do" ;, it from the "to_do" kotlin class that I created
// todos extends from the RecyclerView adapter


    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)//a reference to the view it should hold, which is the itemtodo.xml, what ever is inside the constraintlayout, all the views such as textview,checkbox, will inherit from the recycler view
//viewHolder: holds views, it will hold the layout of a specific item. Behaviour we want to have, recycled behavoir, only the necessary items will be displayed
// in This class, we identify the specific view that it will reference, and it is of type View.


    // Three functions that we adopt from the Recycler view adapter and which we need to implement

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
// This function "OnCreateViewHolder" will create the viewHolder this "TodoViewHolder" we mentioned above
        // Here will define how a specific item in our list will look like.
// This function returns an instance of the Todoviewholder

        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(//layoutInflater will take our itemdo xml file and convert(parse) it to a kotlin class, parent is equal to a viewgroup(& is a layout in android), .inflate() function, will convert our xml layout into real view, takes a 3 parameters
                R.layout.item_todo,// resource id to the layout we want to use
                parent,//
                false// we don't want to attached this to the root layout
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)// we need to notify the adapter that an item was inserted
    }

    fun deleteDoneTodos() {// function to delete done to dos
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()// will updated the whole entire list
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {// we are creating a function
        if(isChecked) {// if it's actually checked we want to strike thru text
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()// remove flag from paint flay
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {//
        // this function will bind the data from our to_do list and our view list
        // This is why RecyclerView takes advantage of the fact that as you scroll and new rows come on screen
        // also old rows disappear off screen. Instead of creating new view for each new row, an old view is recycled and reused by binding new data to it.
        //This happens exactly in onBindViewHolder(). Initially you will get new unused view holders and you have to fill them with data you want to display.
        // But as you scroll you'll start getting view holders that were used for rows that went off screen and you have to replace old data that they held with new data.

        val curTodo = todos[position]// current to do item  and the position referring to index of
        holder.itemView.apply {//our holder and itemview  ( apply helps so we don't have to constantly write the reference
            tvTodoTitle.text = curTodo.title //set's the text to something new,
            cbDone.isChecked = curTodo.isChecked// if checked box is checked
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)//call the function straight through line  and pass tvtodotitle and is checked
            cbDone.setOnCheckedChangeListener { _, isChecked -> // lamdba function, will be called when
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {// returns the amount of items on our list
        return todos.size
    }
}


















