package com.ai_food_checker.user

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.WindowInsetsController
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream

class MainActivity3 : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var btnCopy: LinearLayout
    private lateinit var btnUpload: LinearLayout
    private lateinit var btnScan: LinearLayout
    private lateinit var btnSend: ImageView
    private lateinit var editTextPrompt: EditText
//    private lateinit var mAdView: AdView
    private lateinit var imageView: ImageView
    private lateinit var tVResult: TextView
    private var selectedBitmap: Bitmap? = null

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
        private const val TAKE_PICTURE_REQUEST = 2
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        window.navigationBarColor = Color.parseColor("#000000")
        //icon color -> white
        //icon color -> white
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.setSystemBarsAppearance(0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
//                WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
            )
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.setSystemBarsAppearance(0,
//                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
            )
        }
        progressBar = findViewById(R.id.progress)
        btnCopy = findViewById(R.id.btnCopy)
        btnUpload = findViewById(R.id.btnUpload)
        btnScan = findViewById(R.id.btnScan)
        btnSend = findViewById(R.id.btnSend)
        editTextPrompt = findViewById(R.id.editTextPrompt)
        imageView = findViewById(R.id.imageView)
//        mAdView = findViewById(R.id.adView)
        tVResult = findViewById(R.id.tVResult)
        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)


////         Load the ad
//        val adRequest = AdRequest.Builder().build()
//        mAdView.loadAd(adRequest)



        btnUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        btnScan.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, TAKE_PICTURE_REQUEST)
        }

        btnSend.setOnClickListener {
            val prompt = editTextPrompt.text.toString()
            if (prompt.isEmpty() ) {
                Toast.makeText(this, "Type about scanned picture", Toast.LENGTH_SHORT).show()
            } else {
                // Your code to handle the non-empty case


                // To hide the LottieAnimationView
                lottieAnimationView.visibility = View.GONE
                val prompt = editTextPrompt.text.toString()
                selectedBitmap?.let { bitmap ->
                    generateResponse(prompt, bitmap)
                } ?: Toast.makeText(
                    this,
                    "Please upload or scan an image first",
                    Toast.LENGTH_SHORT
                ).show()

            }
            }

        btnCopy.setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", tVResult.text)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { imageUri ->
                handleImageSelection(imageUri)
            }
        } else if (requestCode == TAKE_PICTURE_REQUEST && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            handleImageCapture(imageBitmap)
        }
    }

    private fun handleImageSelection(imageUri: Uri) {
        GlobalScope.launch(Dispatchers.IO) {
            val imageStream: InputStream? = contentResolver.openInputStream(imageUri)
            val bitmap: Bitmap = BitmapFactory.decodeStream(imageStream)
            imageStream?.close()
            withContext(Dispatchers.Main) {
                selectedBitmap = bitmap
                imageView.setImageBitmap(bitmap)
                imageView.visibility = View.VISIBLE
            }
        }
    }

    private fun handleImageCapture(imageBitmap: Bitmap) {
        GlobalScope.launch(Dispatchers.Main) {
            selectedBitmap = imageBitmap
            imageView.setImageBitmap(imageBitmap)
            imageView.visibility = View.VISIBLE
        }
    }

    private fun generateResponse(prompt: String, bitmap: Bitmap) {
        progressBar.visibility = View.VISIBLE
        tVResult.visibility = View.GONE
        btnCopy.visibility = View.GONE

        GlobalScope.launch(Dispatchers.IO) {
            val chat = ChatData.getResponseWithImage(prompt, bitmap)
            withContext(Dispatchers.Main) {
                tVResult.text = chat.prompt
                progressBar.visibility = View.GONE
                tVResult.visibility = View.VISIBLE
                btnCopy.visibility = View.VISIBLE
            }
        }
    }
}
