package com.arcane.coldstorageexamples.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.arcane.coldstoragecache.callback.OnOperationSuccessfulCallback
import com.arcane.coldstorageexamples.R
import com.arcane.generated.MyBeautifulCacheLayer

class FreezeCacheActivity : AppCompatActivity(), OnOperationSuccessfulCallback<String?> {

    private lateinit var button: Button

    private lateinit var firstRemoteCall: TextView

    private lateinit var secondRemoteCall: TextView

    private val valueArray = arrayListOf("a", "b", "c")

    private val cacheLayer: MyBeautifulCacheLayer = MyBeautifulCacheLayer()
    //
    private var counter = 1
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        firstRemoteCall = findViewById(R.id.remotecall1)
        secondRemoteCall = findViewById(R.id.remotecall2)


        /**
         * On button click random values from the array will be used to make the
         * remote calls.
         * The counter variable is used to control the two calls are made alternatively
         * on button clicks.
         */
        button.setOnClickListener {
            if (counter % 2 == 0) {

                cacheLayer.makeRemoteCallToServiceA(valueArray.random(), this)

            } else {

                cacheLayer.makeRemoteCallToServiceB(
                    valueArray.random(),
                    valueArray.random(),
                    valueArray.random(),
                    this
                )

            }
            counter += 1
        }
    }


    override fun onSuccess(output: String?, operation: String) {

        runOnUiThread {
            when (operation) {
                "makeRemoteCallToServiceA" -> firstRemoteCall.text = output!!
                "makeRemoteCallToServiceB" -> secondRemoteCall.text = output!!
            }
        }
    }
}