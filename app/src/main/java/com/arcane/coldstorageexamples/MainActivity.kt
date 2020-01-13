package com.arcane.coldstorageexamples

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.arcane.coldstoragecache.callback.OnOperationSuccessfulCallback
import com.arcane.coldstorageexamples.remotecall.MakeRemoteCall
import com.arcane.generated.GeneratedCacheLayer

class MainActivity : AppCompatActivity(), OnOperationSuccessfulCallback<String?> {

    /**
     * The make remote call object.
     *
     * @see MakeRemoteCall for details.
     */
    private val makeremoteCall = MakeRemoteCall()

    private lateinit var button: Button

    private lateinit var firstRemoteCall: TextView

    private lateinit var secondRemoteCall: TextView

    private val valueArray = arrayListOf("a", "b", "c")

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
                GeneratedCacheLayer.makeRemoteCallToServiceA(
                    valueArray.random(),
                    makeremoteCall,
                    this
                )
            } else {
                GeneratedCacheLayer.makeRemoteCallToServiceB(
                    valueArray.random(),
                    valueArray.random(),
                    "constantvalue", makeremoteCall, this
                )
            }
            counter += 1
        }
    }

    /**
     * Populate the first text view with the response.
     */
    private fun populateFirstTextView(s: String) {
        runOnUiThread {
            firstRemoteCall.text = s
        }

    }

    /**
     * Populate the second text view with the response.
     */
    private fun populateSecondTextView(s: String) {
        runOnUiThread {
            secondRemoteCall.text = s
        }
    }

    /**
     * The callback implementation via which the result is
     * returned by the generated cache layer.
     */
    override fun onSuccess(output: String?, operation: String) {
        when (operation) {
            "CallToServiceA" -> populateFirstTextView(output!!)
            else ->
                populateSecondTextView(output!!)

        }
    }


}
