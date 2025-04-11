package com.example.florasense

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GetStartedActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)
        enableEdgeToEdge()

        sharedPreferences = getSharedPreferences("UserCredentials", MODE_PRIVATE)

        clearSharedPreferences()

        val button: Button = findViewById(R.id.GetStarted_Button)

        button.setOnClickListener {
            val intent = Intent(this, GetStartedMk2::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.getStarted)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun clearSharedPreferences() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
