package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private var todos: List<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    // Each adapter class needs to have an inner class called viewHolder class.
    // As the name says, it's used to hold the views of the recyclerView.
    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    // It's called when recyclerView needs a new viewHolder i.e. when user scrolls a little bit.
    // An item recycled and it now needs to create a new item which is visible.
    // In this function, we will create a layout for a particular view so for specific item of our recyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        // We need to inflate out item_todo.xml file in oder to get a view so that we can access it from our kotlin file.
        // attachRoot: false cuz we don't want to attach the item_todo view to the root that will cause app crash!!
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    // Purpose of this function is to bind the data to our function.
    // This function will be used to take data from 'todos' list here and set it to the corresponding view(item_todo.xml).
    // holder: to access the view inside the viewHolder
    // position: current index of that particular view that we are binding here and we need to access our 'todos' list here at the same position to get the data about the todos statements that should be at this place in our recyclerView
    // It will be called for each item.
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        // itemView = it contains each single view in our recyclerView
        holder.itemView.apply {

            // Don't know why ain't getting ids automatically that's why did this ):
            val tvTitle = findViewById<TextView>(R.id.tvTitle)
            val cbDone = findViewById<CheckBox>(R.id.cbDone)


            tvTitle.text = todos[position].title
            cbDone.isChecked = todos[position].isChecked
        }
    }

    // Returns how many items we have in our recyclerView.
    override fun getItemCount(): Int {
        return todos.size
    }
}