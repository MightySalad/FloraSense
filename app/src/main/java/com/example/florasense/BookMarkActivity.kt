package com.example.florasense

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class BookMarkActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var bookmarkListView: ListView
    private lateinit var emptyMessageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_mark)

        sharedPreferences = getSharedPreferences("UserCredentials", MODE_PRIVATE)
        bookmarkListView = findViewById(R.id.bookmark_listview)
        emptyMessageTextView = findViewById(R.id.emptyMessageTextView)

        // Get bookmarks, safely handling missing key.
        val bookmarkedPlants = sharedPreferences.getStringSet("bookmarks", mutableSetOf()) ?: mutableSetOf()

        if (bookmarkedPlants.isEmpty()) {
            emptyMessageTextView.visibility = View.VISIBLE
            bookmarkListView.visibility = View.GONE
        } else {
            emptyMessageTextView.visibility = View.GONE
            bookmarkListView.visibility = View.VISIBLE
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bookmarkedPlants.toList())
            bookmarkListView.adapter = adapter
        }

        // Retrieve username and email
        val username = intent.getStringExtra("USERNAME")
        val email = intent.getStringExtra("EMAIL")

        // Back button with simplified lambda function
        val backButton = findViewById<ImageButton>(R.id.Back_ImageButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("USERNAME", username)
                putExtra("EMAIL", email)
            }
            startActivity(intent)
        }
    }
}
