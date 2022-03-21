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

    ): View {
        val rootView: View = inflater.inflate(R.layout.activity_popup,container,false)
        val okBTN: Button = rootView.findViewById(R.id.ok_button)

        okBTN.setOnClickListener{
            dismiss()
        }
        return rootView
    }
}