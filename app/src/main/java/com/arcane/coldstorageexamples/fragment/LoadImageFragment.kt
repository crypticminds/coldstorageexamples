package com.arcane.coldstorageexamples.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.arcane.coldstorageannotation.LoadImage
import com.arcane.coldstoragecache.cache.Cache
import com.arcane.coldstorageexamples.R

class LoadImageFragment : Fragment() {

    @LoadImage(
        R.id.image_1,
        "https://i.ytimg.com/vi/kYnx7nPw-xo/maxresdefault.jpg"
    )
    lateinit var imageView1: ImageView

    @LoadImage(
        R.id.image_2,
        "https://lh3.googleusercontent.com/proxy/JEPQhC8cm7lhnoW56CYbNzS0pHnv08mUNA67AoBknPh-4spJGdIcH0UBwdUewqa1cBd-ESqTdTeqSYiXFT6yzjFgohYzxclpllJq3r3_JOjWSQs",
        placeHolder = R.drawable.loading_2, enableLoadingAnimation = true
    )
    lateinit var imageView2: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.load_image_example_fragment, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Cache.bind(this)
    }
}