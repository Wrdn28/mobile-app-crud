package com.hirokixd.latihanfirestore

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailNewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_news)

        val news = intent.getSerializableExtra("news") as? News
        val titleView = findViewById<TextView>(R.id.detailTitle)
        val descView = findViewById<TextView>(R.id.detailDesc)
        val imageView = findViewById<ImageView>(R.id.detailImage)

        news?.let {
            titleView.text = it.title
            descView.text = it.desc
            Glide.with(this).load(it.img).into(imageView)
        }

    }
}