package com.intentexample

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_play_video.*

class PlayVideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)

        video1.text = "https://www.youtube.com/watch?v=pbQ2k4Q8y0o"

        video2.text = "https://www.youtube.com/watch?v=cV6ucplpmbY"

        video3.text = "https://www.youtube.com/watch?v=dCR0-eLAyNU"

        video1.setOnClickListener({
            startVideo(video1.text.toString())
        })

        video2.setOnClickListener({
            startVideo(video2.text.toString())
        })

        video3.setOnClickListener({
            startVideo(video3.text.toString())
        })

    }

    private fun startVideo( url:String) {
        val startVideo = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startVideo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(startVideo)
    }
}
