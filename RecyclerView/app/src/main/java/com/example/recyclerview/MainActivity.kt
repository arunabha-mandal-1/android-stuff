package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var todoList = mutableListOf<Todo>(
            Todo("Job1", true),
            Todo("Job2", true),
            Todo("Job3", false),
            Todo("Job4", true),
            Todo("Job5", false)
        )

        val adapter = TodoAdapter(todoList)
        val rvTodos = findViewById<RecyclerView>(R.id.rvTodos)
        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(this)

        val btnAddTodo = findViewById<Button>(R.id.btnAddTodo)
        btnAddTodo.setOnClickListener {
            val etTodo = findViewById<EditText>(R.id.etTodo)
            val title = etTodo.text.toString()
            val todo = Todo(title, false)
            todoList.add(todo)

            // update recyclerView
            adapter.notifyItemInserted(todoList.size - 1)

            // This function will update the whole RV cuz it doesn't know the particular position to add the new item.
            // It will go through all items and update them.
            // We should use this function due to inefficiency.
//            adapter.notifyDataSetChanged
        }
    }
}