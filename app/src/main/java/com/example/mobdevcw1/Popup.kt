package com.example.mobdevcw1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class Popup: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        var rootview: View = inflater.inflate(R.layout.activity_popup,container,false)
        var okBTN: Button = rootview.findViewById(R.id.ok_button)

        okBTN.setOnClickListener{
            dismiss()
        }
        return rootview
    }
}