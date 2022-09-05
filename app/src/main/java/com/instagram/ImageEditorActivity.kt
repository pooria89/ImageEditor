package com.instagram

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.instagram.imageeditor.R
import com.instagram.photoediting.*
import com.instagram.photoediting.base.BaseActivity
import ja.burhanrashid52.photoeditor.*


class ImageEditorActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}