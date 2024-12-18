package com.ai_food_checker.user

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ChatwithCharactersAI.user.PromptResponse
import com.ChatwithCharactersAI.user.PromptResponseAdapter
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Chatp : Fragment() {

    private lateinit var spinner: Spinner
    private lateinit var selectedOutputType: String
    private lateinit var recyclerView: RecyclerView
    private val promptResponseList = mutableListOf<PromptResponse>()
    private lateinit var adapter: PromptResponseAdapter
    private lateinit var tvHello: TextView
    private lateinit var mainpmt: String
    private lateinit var eTPrompt1: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chatp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.window?.apply {
            statusBarColor = Color.parseColor("#000000")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                decorView.windowInsetsController?.setSystemBarsAppearance(
                    0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            }
        }

        eTPrompt1 = view.findViewById(R.id.eTPrompt)
        val btnSubmit = view.findViewById<ImageView>(R.id.btnSubmit)
        val tVResult = view.findViewById<TextView>(R.id.tVResult)
        tvHello = view.findViewById(R.id.tvhello)
        spinner = view.findViewById(R.id.spinner)
        recyclerView = view.findViewById(R.id.recyclerView)
        val imageview: ImageView = view.findViewById(R.id.imagviewmain)
//        val profilepic: ImageView = view.findViewById(R.id.iv_image)
//        val profilename: TextView = view.findViewById(R.id.buddybottext)
        val hashtag: TextView = view.findViewById(R.id.hashtag)

        val items = resources.getStringArray(R.array.spinner_items).toMutableList()
        val spinnerAdapter = CustomSpinnerAdapter(
            requireContext(), android.R.layout.simple_spinner_item, items, Color.BLACK, Color.WHITE
        )
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

        val value = arguments?.getInt("key", -1) ?: -1

        btnSubmit.setOnClickListener {
            tvHello.visibility = View.INVISIBLE
            mainpmt = eTPrompt1.text.toString()



                tvHello.text = "Hello I am  what about you?"
                val prompt = "you are an persional assistant who is ${hashtag.text}. You have to talk with user and user's prompt is $mainpmt"
                fun1(prompt, tVResult)


            eTPrompt1.text.clear()
        }

        recyclerView.layoutManager = LinearLayoutManager(context).apply { stackFromEnd = true }
        adapter = PromptResponseAdapter(promptResponseList)
        recyclerView.adapter = adapter
    }

    private fun fun1(prompt: String, resultTextView: TextView) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            val promptResponse = PromptResponse(mainpmt, "Typing...")
            promptResponseList.add(promptResponse)
            adapter.notifyItemInserted(promptResponseList.size - 1)
            recyclerView.scrollToPosition(promptResponseList.size - 1)

            val responseText = withContext(Dispatchers.IO) { fetchContentFromAPI(prompt) }
            val position = promptResponseList.indexOf(promptResponse)
            promptResponseList[position].response = responseText
            adapter.notifyItemChanged(position)
            recyclerView.scrollToPosition(position)
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
            val responseText = response.text?.replace("**", "")?.replace("*", "")

            responseText ?: ""
        } catch (e: Exception) {
            Log.e("API_ERROR", "Error fetching data: ${e.message}", e)
            "Error fetching data: ${e.message}"
        }
    }
}
