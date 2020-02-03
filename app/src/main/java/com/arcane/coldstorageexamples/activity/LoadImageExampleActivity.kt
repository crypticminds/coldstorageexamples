package com.arcane.coldstorageexamples.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.arcane.coldstorageannotation.LoadImage
import com.arcane.coldstoragecache.cache.Cache
import com.arcane.coldstorageexamples.R

class LoadImageExampleActivity : AppCompatActivity() {

    @LoadImage(
        R.id.image_1,
        "https://images.unsplash.com/photo-1549740425-5e9ed4d8cd34?ixlib=rb-1.2.1&w=1000&q=80",
        placeHolder = R.drawable.loading, enableLoadingAnimation = true
    )
    lateinit var imageWithAnimation: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.load_image_example)
        Cache.bind(this)
    }
}