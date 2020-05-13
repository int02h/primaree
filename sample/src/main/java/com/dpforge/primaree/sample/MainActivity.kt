package com.dpforge.primaree.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dpforge.primaree.runIfPrimaryProcess
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_run_service.setOnClickListener {
            SampleService.start(
                this@MainActivity
            )
        }
        btn_send_broadcast.setOnClickListener {
            SampleReceiver.sendBroadcast(
                this@MainActivity
            )
        }
        btn_start_activity.setOnClickListener {
            SampleActivity.start(
                this@MainActivity
            )
        }
        btn_query_provider.setOnClickListener {
            SampleContentProvider.query(
                this@MainActivity
            )
        }

        application.runIfPrimaryProcess {
            log("MainActivity is running in primary process")
        }
    }
}
