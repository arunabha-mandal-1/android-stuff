package com.arunabha.repositorypattern.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.arunabha.repositorypattern.data.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    // annotation processor will do its task
    abstract fun taskDao(): TaskDao

    // thread safe singleton pattern
    companion object {

        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase? {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        initDummyData(context)
                        super.onCreate(db)
                    }
                    fun initDummyData(context: Context) {
                        CoroutineScope(Dispatchers.IO).launch{
                            val taskDao = getDatabase(context)?.taskDao()
                            val task1 = Task(0, "Task1", "Arunabha Mandal")
                            val task2 = Task(1, "Task2", "Kittu Mandal")
                            val task3 = Task(2, "Task3", "Babai Mandal")
                            taskDao?.insertTask(task1)
                            taskDao?.insertTask(task2)
                            taskDao?.insertTask(task3)
                        }


                    }
                }).build()
                INSTANCE = instance
                INSTANCE
            }
        }
    }
}