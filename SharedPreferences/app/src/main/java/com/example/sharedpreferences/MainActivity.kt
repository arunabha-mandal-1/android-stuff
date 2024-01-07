package com.example.sharedpreferences

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var etName: TextInputEditText
    private lateinit var etAge: TextInputEditText
    private lateinit var cbAdult: CheckBox
    private lateinit var btnLoad: Button
    private lateinit var btnSave: Button
    private lateinit var btnGoTOSecondActivity: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        cbAdult = findViewById(R.id.checkBox)
        btnLoad = findViewById(R.id.btnLoad)
        btnSave = findViewById(R.id.btnSave)
        btnGoTOSecondActivity = findViewById(R.id.btnGoToSecondActivity)

        // It is used for really small amount of data, just to save preferences
        // there are 3 modes
        // public: it will make the file public we save our shared preferences in, every other app can use this
        // private: no other app can use the preferences
        // append: it will take existing preferences and append new preferences to those

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString().toInt()
            val isAdult = cbAdult.isChecked

            editor.apply {
                putString("name", name)
                putInt("age", age)
                putBoolean("isAdult", isAdult)

                // commit will save data synchronously, so it will block main thread
//                commit()

                // apply will do it asynchronously
                apply()
            }
        }

        btnLoad.setOnClickListener {
            val name = sharedPref.getString("name", null)
            val age = sharedPref.getInt("age", 0)
            val adult = sharedPref.getBoolean("isAdult", false)

            etName.setText(name)
            etAge.setText(age.toString())
            cbAdult.isChecked = adult
        }

        btnGoTOSecondActivity.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}