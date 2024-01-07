package com.example.roomdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.roomdb.converter.DateTypeConverter
import com.example.roomdb.converter.ListConverter
import com.example.roomdb.dao.ExerciseDao
import com.example.roomdb.dao.GenderDao
import com.example.roomdb.dao.RoutineDao
import com.example.roomdb.dao.TraineeDao
import com.example.roomdb.model.Exercise
import com.example.roomdb.model.Gender
import com.example.roomdb.model.Routine
import com.example.roomdb.model.Trainee

@Database(entities = [Exercise::class, Gender::class, Routine::class, Trainee::class], version = 1)
@TypeConverters(DateTypeConverter::class, ListConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun genderDao(): GenderDao
    abstract fun routineDao(): RoutineDao
    abstract fun traineeDao(): TraineeDao

    companion object {
        var INSTANCE: AppDatabase? = null
        fun getAppDataBase(context: Context): AppDatabase?{
            if(INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabase(){
            INSTANCE = null
        }
    }
}