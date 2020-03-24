package com.dpforge.primaree.sample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SampleActivity::class.java))
        }
    }
}
