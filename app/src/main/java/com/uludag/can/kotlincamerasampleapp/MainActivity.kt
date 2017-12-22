package com.uludag.can.kotlincamerasampleapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStartTakePhotoActivity.setOnClickListener(this)
        btnStartRecordVideoActivity.setOnClickListener(this)
        btnStartWebViewExample.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnStartTakePhotoActivity -> {
                val intent = Intent(this, TakePhotoActivity::class.java)
                startActivity(intent)
            }
            R.id.btnStartRecordVideoActivity -> {
                val intent = Intent(this, RecordVideoActivity::class.java)
                startActivity(intent)
            }
            R.id.btnStartWebViewExample -> {
                val intent = Intent(this, WebViewExampleActivity::class.java)
                startActivity(intent)
            }
        }
    }


}
