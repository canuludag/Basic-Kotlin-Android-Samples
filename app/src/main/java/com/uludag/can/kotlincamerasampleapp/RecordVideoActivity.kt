package com.uludag.can.kotlincamerasampleapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_record_video.*

class RecordVideoActivity : AppCompatActivity() {

    private val requestVideoAppRequestCode = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_video)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = "Record Video"

        videoButton.setOnClickListener {
            callVideoApp()
        }

        videoView.setOnCompletionListener {
            videoView.visibility = View.GONE
        }

    }

    // Starts video intent
    fun callVideoApp() {
        val videoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if (videoIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(videoIntent, requestVideoAppRequestCode)
        }
    }

    // Returning from video intent
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            requestVideoAppRequestCode -> {

                if (resultCode == Activity.RESULT_OK && data != null) {
                    videoView.setVideoURI(data.data)
                    videoView.visibility = View.VISIBLE
                    videoView.start()
                }

            }
            else -> {
                Toast.makeText(this, "Undefined request code!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
