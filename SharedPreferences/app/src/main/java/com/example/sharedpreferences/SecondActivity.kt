package com.example.sharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class SecondActivity : AppCompatActivity() {

    private lateinit var etAnotherName: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var btnLoad: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        etAnotherName = findViewById(R.id.etAnotherName)
        btnSave = findViewById(R.id.btnSave)
        btnLoad = findViewById(R.id.btnLoad)

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        btnLoad.setOnClickListener {
            val name = sharedPref.getString("name", null)
            etAnotherName.setText(name)
        }

        btnSave.setOnClickListener {
            val name = etAnotherName.text.toString()
            editor.apply {
                putString("name", name)
                apply()
            }
        }
    }
}