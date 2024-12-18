
package com.ChatwithCharactersAI.user

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.ai.client.generativeai.GenerativeModel
import com.ai_food_checker.user.CustomSpinnerAdapter
import com.ai_food_checker.user.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class chatlayout : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var selectedOutputType: String
    private lateinit var recyclerView: RecyclerView
    private val promptResponseList = mutableListOf<PromptResponse>()
    private lateinit var adapter: PromptResponseAdapter
    private lateinit var tvHello: TextView

    private lateinit var mainpmt: String
    private lateinit var eTPrompt1: EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatlayout)
        window.statusBarColor = Color.parseColor("#000000")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)
        }

        eTPrompt1 = findViewById(R.id.eTPrompt)
        val btnSubmit = findViewById<ImageView>(R.id.btnSubmit)
        val tVResult = findViewById<TextView>(R.id.tVResult)
        tvHello = findViewById(R.id.tvhello)
        spinner = findViewById(R.id.spinner)
        recyclerView = findViewById(R.id.recyclerView)
        val imageview: ImageView = findViewById(R.id.imagviewmain)
        val profilepic: ImageView = findViewById(R.id.iv_image)
        val profilename: TextView = findViewById(R.id.buddybottext)
        val hashtag: TextView = findViewById(R.id.hashtag)
        val greeting: String = "cool"
        val userName: String = "Helpfull"


        val items: MutableList<String> = arrayListOf("Practice", "Preparation", "Research", "Integration", "Extension")
        val spinnerAdapter = CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, items, Color.BLACK, Color.WHITE)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedOutputType = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing here
            }
        }
        val value = intent.getIntExtra("key", -1)
//        if (value == 1) {
//            imageview.setImageResource(R.drawable.anime1)
//            profilepic.setImageResource(R.drawable.anime1)
//            profilename.text = "soli"
//            hashtag.text = "Friendly and " + "Love"
//            tvHello.text = "Hello i am " + profilename.text + " what about you"
//
//        }
        btnSubmit.setOnClickListener {
            // Make the TextView invisible
            tvHello.visibility = View.INVISIBLE
//
//            val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
            mainpmt = eTPrompt1.text.toString()
//            fun1(prompt, tVResult,mainpmt)
//            // Clear the EditText content
//            eTPrompt1.text.clear()

            val value = intent.getIntExtra("key", -1)
            if (value == 0) {
                profilename.text = "Jecika"
                tvHello.text="Hello i am "+profilename.text+" what about you "
                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            }
            if (value == 1) {


                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 2) {
                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 3) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()

                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 4) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 5) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 6) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 7) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 8) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 9) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 10) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 11) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 12) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 13) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 14) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 15) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 16) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 17) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 18) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 19) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 20) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 21) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is" + hashtag.text.toString() + "you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            } else if (value == 22) {

                val prompt = "you are a ai character name" +profilename.text.toString()+" who is you have to talk with user and user promt is" + eTPrompt1.text.toString()
                mainpmt = eTPrompt1.text.toString()
                fun1(prompt, tVResult,mainpmt)
            }
            eTPrompt1.text.clear()
        }
        // Initialize RecyclerView with reversed layout
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager
        this.adapter = PromptResponseAdapter(promptResponseList)
        recyclerView.adapter = this.adapter
    }

    private fun fun1(prompt: String, resultTextView: TextView,mainpmt: String) {
        GlobalScope.launch(Dispatchers.Main) {
            // Add prompt to the list with a "Typing..." response initially
            val promptResponse = PromptResponse(mainpmt, "Typing...")

            promptResponseList.add(promptResponse)
            adapter.notifyItemInserted(promptResponseList.size - 1)
            recyclerView.scrollToPosition(promptResponseList.size - 1)  // Scroll to the bottom

            // Simulate network delay
            val responseText = fetchContentFromAPI(prompt)

            // Update the response for the prompt in the list
            val position = promptResponseList.indexOf(promptResponse)
            promptResponseList[position].response = responseText
            adapter.notifyItemChanged(position)
            recyclerView.scrollToPosition(position)  // Scroll to the updated item
        }
    }

    private suspend fun fetchContentFromAPI(prompt: String): String {
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = "AIzaSyD2b6YvKBAClXqsHDoKUPlRb2zbHFsO_PA"
        )

        return try {
            // Simulate a delay for the network call
            delay(3000)
            val response = generativeModel.generateContent(prompt)
            var responseText = response.text?.replace("**", "")?.replace("*", "")

            if (responseText != null && responseText.contains("**")) {
                val spannable = SpannableString(responseText)
                spannable.setSpan(StyleSpan(Typeface.BOLD), 0, responseText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannable.toString()
            } else {
                responseText ?: ""
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching data: ${e.message}", e)
            "Error fetching data: ${e.message}"
        }
    }


}
