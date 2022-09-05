package com.instagram

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.instagram.imageeditor.ImageEditorActivity
import com.instagram.imageeditor.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val intent = Intent(this, ImageEditorActivity::class.java)
        startActivity(intent)
    }
}