package com.example.recyclerview

// TodoAdapter needs to know which data it should set to which item.
// To actually tell the adapter that, we needs to create an another class that describes how a todoList item should looks like.
// That's why we create this class.
data class Todo(
    val title: String,
    var isChecked: Boolean
)