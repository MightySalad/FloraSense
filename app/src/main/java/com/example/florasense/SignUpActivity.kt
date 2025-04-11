package com.example.florasense

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val backButton: ImageButton = findViewById(R.id.back_button)
        val username = findViewById<EditText>(R.id.Username_EditText)
        val email = findViewById<EditText>(R.id.Email_EditText)
        val password = findViewById<EditText>(R.id.Password_EditText)
        val confirmPassword = findViewById<EditText>(R.id.ConfirmPassword_EditText)
        val signupButton: Button = findViewById(R.id.SignUp_Button)
        val alreadyHaveAccount = findViewById<TextView>(R.id.AlreadyHaveAccount)

        backButton.setOnClickListener {
            val intent = Intent(this, GetStartedMk2::class.java)
            startActivity(intent)
        }

        signupButton.setOnClickListener {
            val usernameEditText = username.text.toString()
            val emailEditText = email.text.toString()
            val passwordEditText = password.text.toString()
            val confirmPasswordEditText = confirmPassword.text.toString()

            if (usernameEditText.isEmpty() || emailEditText.isEmpty() || passwordEditText.isEmpty() || confirmPasswordEditText.isEmpty()) {
                Toast.makeText(this, "All Fields are Required", Toast.LENGTH_SHORT).show()
            } else if (passwordEditText != confirmPasswordEditText) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                val sharedPreferences = getSharedPreferences("UserCredentials", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString(emailEditText, passwordEditText)
                editor.putString("$emailEditText-username", usernameEditText)
                editor.apply()

                val intent = Intent(this, LogInActivity::class.java)
                Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
        }

        alreadyHaveAccount.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
    }
}
