package com.arunabha.repositorypattern.repository

import android.content.Context
import androidx.compose.runtime.collectAsState
import com.arunabha.repositorypattern.data.Task
import com.arunabha.repositorypattern.database.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext

class TaskRepository(private val context: Context, private val taskDao: TaskDao) {
    private val contextForRepository = context.applicationContext
    var allTasks: Flow<List<Task>> =  taskDao.getAllTasksAsFlow()

    suspend fun getAllTasks(): Flow<List<Task>> = withContext(Dispatchers.IO) {
        if (allTasks.toList().isNotEmpty()) {
            return@withContext allTasks
        } else {
            allTasks = taskDao.getAllTasksAsFlow()
            return@withContext allTasks
        }
    }

    suspend fun getTaskById(id: Int): Task? = withContext(Dispatchers.IO) {
        try {
            return@withContext taskDao.getTaskById(id)
        } catch (e: Throwable) {
            return@withContext null
        }
    }

    suspend fun insertTask(task: Task): Boolean = withContext(Dispatchers.IO) {
        try {
            taskDao.insertTask(task)
            allTasks = getAllTasks()
            return@withContext true
        } catch (e: Throwable) {
            return@withContext false
        }
    }

    suspend fun deleteTask(task: Task): Boolean = withContext(Dispatchers.IO) {
        try {
            taskDao.deleteTask(task)
            allTasks = getAllTasks()
            return@withContext true
        } catch (e: Throwable) {
            return@withContext false
        }
    }

    suspend fun updateTask(task: Task): Boolean = withContext(Dispatchers.IO) {
        try {
            taskDao.updateTask(task)
            allTasks = getAllTasks()
            return@withContext true
        } catch (e: Throwable) {
            return@withContext false
        }
    }

}