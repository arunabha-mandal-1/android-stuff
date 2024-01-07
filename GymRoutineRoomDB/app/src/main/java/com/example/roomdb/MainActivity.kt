package com.example.roomdb

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdb.dao.GenderDao
import com.example.roomdb.database.AppDatabase
import com.example.roomdb.model.Gender
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var genderDao: GenderDao? = null
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coroutineScope.launch {
            try {
                val list = performDatabaseOperations(applicationContext)

                val finalString = list?.joinToString(" - ") { it.name }
                findViewById<TextView>(R.id.message).text = finalString
            } catch (e: Exception) {
                // Handle exceptions if needed
                Log.e("Arunabha", "Error performing database operations", e)
            }
        }

    }

    suspend fun performDatabaseOperations(context: Context): List<Gender>? {
        return try {
            db = AppDatabase.getAppDataBase(context)
            genderDao = db?.genderDao()

            var gender1 = Gender(name = "Male")
            var gender2 = Gender(name = "Female")

            with(genderDao) {
                this?.insertGender(gender1)
                this?.insertGender(gender2)
            }

            db?.genderDao()?.getGenders()
        } finally {
            // Close or release resources if needed
        }
    }
}