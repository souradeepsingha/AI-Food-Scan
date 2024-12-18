package com.ai_food_checker.user

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class askq2 : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    lateinit var btnCopy: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_askq2)
        window.statusBarColor = Color.parseColor("#000000")
        //icon color -> white
        //icon color -> white
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.setSystemBarsAppearance(0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
//                WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
            )
        }
        progressBar = findViewById(R.id.progress2)
        btnCopy = findViewById(R.id.btnCopy2)
        val tVResult = findViewById<TextView>(R.id.tVResult2)

        val prompt = intent.getStringExtra("userInput")

        btnCopy.setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", tVResult.text)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
        }

        fun1(prompt.toString(), tVResult)
    }

    private fun fun1(prompt: String, resultTextView: TextView) {
        GlobalScope.launch(Dispatchers.Main) {
            val responseText = fetchContentFromAPI(prompt)
            resultTextView.text = responseText
            progressBar.visibility = View.GONE
            btnCopy.visibility= View.VISIBLE
        }
    }

    private suspend fun fetchContentFromAPI(prompt: String): String {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = "AIzaSyD2b6YvKBAClXqsHDoKUPlRb2zbHFsO_PA"
        )

        try {
            val response = generativeModel.generateContent(prompt)
            var responseText = response.text

            // Remove "**" and "*" from the response text
            responseText = responseText?.replace("**", "")?.replace("*", "")

            // Check if the response contains "**" and make the entire response bold
            if (responseText != null && responseText.contains("**")) {
                val spannable = SpannableString(responseText)
                spannable.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    responseText.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                return spannable.toString()
            }

            return responseText ?: ""
        } catch (e: Exception) {
            // Log the error
            Log.e(ContentValues.TAG, "Error fetching data: ${e.message}", e)
            // Return an error message
            return "Error fetching data: ${e.message}"
        }
    }
}