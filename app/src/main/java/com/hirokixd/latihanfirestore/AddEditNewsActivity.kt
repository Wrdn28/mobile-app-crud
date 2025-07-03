package com.hirokixd.latihanfirestore

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddEditNewsActivity : AppCompatActivity() {

    private val db = Firebase.firestore
    private var existingNews: News? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_news)

        val titleEdit = findViewById<EditText>(R.id.titleEdit)
        val shortDescEdit = findViewById<EditText>(R.id.shortDescEdit)
        val descEdit = findViewById<EditText>(R.id.descEdit)
        val imgEdit = findViewById<EditText>(R.id.imgEdit)
        val saveButton = findViewById<Button>(R.id.saveButton)

        val existingNews = intent.getSerializableExtra("news") as? News
        existingNews?.let {
            titleEdit.setText(it.title)
            shortDescEdit.setText(it.short_desc)
            descEdit.setText(it.desc)
            imgEdit.setText(it.img)
        }

        saveButton.setOnClickListener {
            val news = News(
                title = titleEdit.text.toString(),
                short_desc = shortDescEdit.text.toString(),
                desc = descEdit.text.toString(),
                img = imgEdit.text.toString()
            )

            val id = existingNews?.id ?: db.collection("news").document().id
            db.collection("news").document(id).set(news)
                .addOnSuccessListener {
                    Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show()
                }
        }
    }
}