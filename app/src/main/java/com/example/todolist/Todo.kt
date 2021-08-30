package com.example.todolist

data class Todo(
    val title: String,// title of that to do item which is a string
    var isChecked: Boolean = false// a var that determines is this item is checked,
    // by default the new item is not checked, so we will set it to false

//This is a data Class and primary purpose is to hold data
)//constructor