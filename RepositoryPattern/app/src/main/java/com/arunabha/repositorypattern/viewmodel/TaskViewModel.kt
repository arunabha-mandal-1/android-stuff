package com.arunabha.repositorypattern.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arunabha.repositorypattern.data.Task
import com.arunabha.repositorypattern.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {
//    val allTasks: MutableState<List<Task>> = mutableStateOf(emptyList())
//    val isLoading: MutableState<Boolean> = mutableStateOf(false)

//    private val _allTasks = MutableStateFlow<List<Task>>(emptyList())
//    val allTasks: StateFlow<List<Task>> get() = _allTasks

    var allTasksFlow: Flow<List<Task>> = taskRepository.allTasks

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private suspend fun getAllTasks() {
        Log.d("abc", _isLoading.value.toString()) // printed
        allTasksFlow = taskRepository.getAllTasks()
        _isLoading.value = false
        Log.d("abc", _isLoading.value.toString()) // not printed
    }

    suspend fun getTaskById(id: Int): Task? {
        return taskRepository.getTaskById(id)
    }

    suspend fun insertTask(task: Task): Boolean {
        val status = taskRepository.insertTask(task)
        getAllTasks()
        return status
    }

    suspend fun deleteTask(task: Task): Boolean {
        val status = taskRepository.deleteTask(task)
        getAllTasks()
        return status
    }

    suspend fun updateTask(task: Task): Boolean {
        val status = taskRepository.updateTask(task)
        getAllTasks()
        return status
    }

    init {
        viewModelScope.launch {
            getAllTasks()
//            isLoading.value = true
        }
    }
}