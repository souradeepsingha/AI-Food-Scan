package com.ai_food_checker.user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast

class homemain : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myview = inflater.inflate(R.layout.fragment_homemain, container, false)

       val scanpic0: LinearLayout = myview.findViewById(R.id.scanpic0)
        scanpic0.setOnClickListener {
            Toast.makeText(activity, "Coming soon", Toast.LENGTH_SHORT).show()
        }

        val scanpic: LinearLayout = myview.findViewById(R.id.scanpic)

        scanpic.setOnClickListener {
            val intent = Intent(activity, scanpicactivity::class.java)

            startActivity(intent)
        }
        val uploadpic: LinearLayout = myview.findViewById(R.id.uploadpic)

        uploadpic.setOnClickListener {
            val intent = Intent(activity, uploadpicactivity::class.java)

            startActivity(intent)
        }


        val solvequestion: LinearLayout = myview.findViewById(R.id.solve_question)

        solvequestion.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)

            startActivity(intent)
        }
        val solvequestion4: LinearLayout = myview.findViewById(R.id.solve_question4)
        // Inflate the layout for this fragment
        solvequestion4.setOnClickListener {
            val intent = Intent(activity, MainActivity3::class.java)

            startActivity(intent)
        }
        return  myview
    }


}