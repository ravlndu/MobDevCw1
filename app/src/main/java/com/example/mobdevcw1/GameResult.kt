package com.example.mobdevcw1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class GameResult : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        var rootview: View = inflater.inflate(R.layout.activity_popup,container,false)
        var okBTN: Button = rootview.findViewById(R.id.ok_button)
        val dataPassed: String? = arguments?.getString("content")
        val resultTxt: TextView = rootview.findViewById((R.id.resultTXT))
        resultTxt.text=dataPassed
        okBTN.setOnClickListener{
            dismiss()
        }
        return rootview
    }
}