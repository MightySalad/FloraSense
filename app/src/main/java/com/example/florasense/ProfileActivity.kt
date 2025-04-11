package com.example.florasense

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val username = intent.getStringExtra("USERNAME")
        val email = intent.getStringExtra("EMAIL")

        val usernameTextView = findViewById<TextView>(R.id.username_text)
        val emailTextView = findViewById<TextView>(R.id.email_text)
        val usernameBoxTextView = findViewById<TextView>(R.id.usernameBox_text)
        val emailBoxTextView = findViewById<TextView>(R.id.emailBox_text)

        usernameTextView.text = username ?: "Unknown User"
        emailTextView.text = email ?: "Unknown Email"
        usernameBoxTextView.text = username ?: "Unknown User"
        emailBoxTextView.text = email ?: "Unknown Email"

        val settingsButton = findViewById<ImageButton>(R.id.settings_imageButton)
        val backButton = findViewById<ImageButton>(R.id.Back_ImageButton)
        val logOutButton = findViewById<Button>(R.id.logout_button)

        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USERNAME", username)
            intent.putExtra("EMAIL", email)
            startActivity(intent)
        }

        logOutButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Logout Confirmation")
            builder.setMessage("Are you sure you want to logout?")
            builder.setCancelable(false)

            builder.setPositiveButton("YES") { _, _ ->
                Toast.makeText(this, "Successfully Logged Out", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LogInActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }

            builder.setNegativeButton("NO") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
    }
}
