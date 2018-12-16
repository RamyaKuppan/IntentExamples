package com.intentexample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alarm.setOnClickListener({
            val alarmActivity = Intent(this, AlarmActivity::class.java)
            startActivity(alarmActivity)
        })

        playVideo.setOnClickListener({
            val playVideoIntent = Intent(this, PlayVideoActivity::class.java)
            startActivity(playVideoIntent)

        })

        camera.setOnClickListener({
            val cameraActivity = Intent(this, CameraActivity::class.java)
            startActivity(cameraActivity)
        })

        /*share.setOnClickListener({
            val playVideoIntent = Intent(this, ShareActivity::class.java)
            startActivity(playVideoIntent)
        })*/
    }
}
