package com.example.florasense

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("UserCredentials", MODE_PRIVATE)

        val username = intent.getStringExtra("USERNAME")
        val email = intent.getStringExtra("EMAIL")

        val profileButton = findViewById<ImageButton>(R.id.Profile_ImageButton)
        val bookmarkButton = findViewById<ImageButton>(R.id.Bookmark_ImageButton)

        val bookmark1 = findViewById<RelativeLayout>(R.id.bookmark)
        val bookmark2 = findViewById<RelativeLayout>(R.id.bookmark2)
        val bookmark3 = findViewById<RelativeLayout>(R.id.bookmark3)
        val bookmark4 = findViewById<RelativeLayout>(R.id.bookmark4)

        val moreInfoButton1 = findViewById<Button>(R.id.moreInfo_Button)
        val moreInfoButton2 = findViewById<Button>(R.id.moreInfo_Button2)
        val moreInfoButton3 = findViewById<Button>(R.id.moreInfo_Button3)
        val moreInfoButton4 = findViewById<Button>(R.id.moreInfo_Button4)

        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("USERNAME", username)
            intent.putExtra("EMAIL", email)
            startActivity(intent)
        }

        bookmarkButton.setOnClickListener {
            val intent = Intent(this, BookMarkActivity::class.java)
            intent.putExtra("USERNAME", username)
            intent.putExtra("EMAIL", email)
            startActivity(intent)
        }

        bookmark1.setOnClickListener { toggleBookmark("Snake Plant") }
        bookmark2.setOnClickListener { toggleBookmark("Monstera Plant") }
        bookmark3.setOnClickListener { toggleBookmark("False Shamrock") }
        bookmark4.setOnClickListener { toggleBookmark("Barrel Cactus") }

        moreInfoButton1.setOnClickListener {
            showPlantInfoDialog("Snake Plant", "A hardy plant that can survive low light and neglect.", R.drawable.img_1)
        }
        moreInfoButton2.setOnClickListener {
            showPlantInfoDialog("Monstera Plant", "A tropical plant with large, split leaves.", R.drawable.img_3)
        }
        moreInfoButton3.setOnClickListener {
            showPlantInfoDialog("False Shamrock", "A small ornamental plant with purple leaves.", R.drawable.img_4)
        }
        moreInfoButton4.setOnClickListener {
            showPlantInfoDialog("Barrel Cactus", "A desert plant known for its round shape and spines.", R.drawable.img_5)
        }
    }

    private fun toggleBookmark(plantName: String) {
        val currentBookmarks = sharedPreferences.getStringSet("bookmarks", mutableSetOf()) ?: mutableSetOf()

        if (currentBookmarks.contains(plantName)) {
            currentBookmarks.remove(plantName)
            Toast.makeText(this, "$plantName removed from bookmarks", Toast.LENGTH_SHORT).show()
        } else {
            currentBookmarks.add(plantName)
            Toast.makeText(this, "$plantName added to bookmarks", Toast.LENGTH_SHORT).show()
        }

        val editor = sharedPreferences.edit()
        editor.putStringSet("bookmarks", currentBookmarks)
        editor.apply()
    }

    private fun showPlantInfoDialog(plantName: String, plantDescription: String, plantImage: Int) {
        val dialogView = layoutInflater.inflate(R.layout.plant_info, null)
        val nameTextView = dialogView.findViewById<TextView>(R.id.plant_name)
        val descriptionTextView = dialogView.findViewById<TextView>(R.id.plant_description)
        val plantImageView = dialogView.findViewById<ImageView>(R.id.plant_image)

        nameTextView.text = plantName
        descriptionTextView.text = plantDescription
        plantImageView.setImageResource(plantImage)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .create()

        dialog.show()
    }
}
