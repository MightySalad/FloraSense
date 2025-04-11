package com.example.florasense

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val backButton = findViewById<ImageButton>(R.id.back_button)
        val loginButton = findViewById<Button>(R.id.Login_Button)
        val dontHaveAccount = findViewById<TextView>(R.id.DontHaveAccount)

        val emailEditText = findViewById<EditText>(R.id.Email_EditText)
        val passwordEditText = findViewById<EditText>(R.id.Password_EditText)

        backButton.setOnClickListener {
            val intent = Intent(this, GetStartedMk2::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val enteredEmail = emailEditText.text.toString()
            val enteredPassword = passwordEditText.text.toString()

            if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "All Fields are Required", Toast.LENGTH_SHORT).show()
            } else {
                val sharedPreferences = getSharedPreferences("UserCredentials", MODE_PRIVATE)
                val storedPassword = sharedPreferences.getString(enteredEmail, null)

                if (storedPassword == null) {
                    Toast.makeText(this, "Account does not exist", Toast.LENGTH_SHORT).show()
                } else if (storedPassword == enteredPassword) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    val username = sharedPreferences.getString("$enteredEmail-username", "Unknown User")
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("USERNAME", username)
                    intent.putExtra("EMAIL", enteredEmail)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        dontHaveAccount.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
