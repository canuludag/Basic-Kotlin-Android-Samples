package com.uludag.can.kotlincamerasampleapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_take_photo.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class TakePhotoActivity : AppCompatActivity() {

    private val cameraRequestCode = 0
    private lateinit var imageFilePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_photo)
        setupSupportActionbar()

        // Definition handled with Kotlin Extensions
        cameraButton.setOnClickListener {

            try {
                val imageFile = createImageFile()
                val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (callCameraIntent.resolveActivity(packageManager) != null) {
                    val authorities = packageName + ".fileprovider"
                    val imageUri = FileProvider.getUriForFile(this, authorities, imageFile)
                    callCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                    startActivityForResult(callCameraIntent, cameraRequestCode)
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Could not create file!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun setupSupportActionbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = "Take Photo"
    }

    // Returning from the camera intent
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            cameraRequestCode -> {

                if(resultCode == Activity.RESULT_OK) {
                    photoImageView.setImageBitmap(getScaledBitmap())
                }

            }
            else -> {
                Toast.makeText(this, "Undefined request code", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Creates the image file with a timestamp
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.GERMANY).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }
        val imageFile = createTempFile(imageFileName, ".jpg", storageDir)
        imageFilePath = imageFile.absolutePath

        return imageFile
    }

    // Scales the bitmap that return from camera and returns a scaled bitmap for imageview
    private fun getScaledBitmap(): Bitmap {

        val imageViewWidth = photoImageView.width
        val imageViewHeight = photoImageView.height

        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(imageFilePath, bmOptions)
        val bitmapWidth = bmOptions.outWidth
        val bitmapHeight = bmOptions.outHeight

        val scaleFactor = Math.min(bitmapWidth / imageViewWidth, bitmapHeight / imageViewHeight)
        bmOptions.inSampleSize = scaleFactor
        bmOptions.inJustDecodeBounds = false

        return BitmapFactory.decodeFile(imageFilePath, bmOptions)

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
