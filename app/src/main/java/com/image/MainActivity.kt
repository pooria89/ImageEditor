package com.image

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.image.imageeditor.R
import com.instagram.imageeditor.ImageEditorActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        ImageEditorActivity.start(this)
    }
}