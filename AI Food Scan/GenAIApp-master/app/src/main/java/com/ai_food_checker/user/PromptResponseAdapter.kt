package com.ChatwithCharactersAI.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ai_food_checker.user.R

class PromptResponseAdapter(private val promptResponseList: MutableList<PromptResponse>) :
    RecyclerView.Adapter<PromptResponseAdapter.PromptResponseViewHolder>() {

    class PromptResponseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val promptTextView: TextView = view.findViewById(R.id.tvPrompt)
        val responseTextView: TextView = view.findViewById(R.id.tvResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromptResponseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prompt_response, parent, false)
        return PromptResponseViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromptResponseViewHolder, position: Int) {
        val item = promptResponseList[position]
        holder.promptTextView.text = item.prompt
        holder.responseTextView.text = item.response
    }

    override fun getItemCount() = promptResponseList.size

    fun updateResponseAt(position: Int, response: String) {
        promptResponseList[position].response = response
        notifyItemChanged(position)
    }
}
