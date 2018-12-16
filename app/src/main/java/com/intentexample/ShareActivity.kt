package com.intentexample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_share.*


class ShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        when {
            intent?.action == Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    handleSendText(intent)
                }
            }
            else -> {
                // Handle other intents, such as being started from the home screen
            }
        }

    }

    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            text.text = it
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.send, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.send -> {
                var min = 0
                var max = text.text.length
                if (text.isFocused) {
                    val selStart = text.selectionStart
                    val selEnd = text.selectionEnd

                    min = Math.max(0, Math.min(selStart, selEnd))
                    max = Math.max(0, Math.max(selStart, selEnd))
                }

                val selectedText = text.text.subSequence(min, max)

                val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, selectedText)
                startActivity(Intent.createChooser(sharingIntent, "Share using"))

            }
            android.R.id.home -> {
                finish()
            }
        }
        return false
    }
}
