package com.example.mobdevcw1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val aboutbtn = findViewById<Button>(R.id.about_btn)
        val newgamebtn = findViewById<Button>(R.id.newgame_btn)

        // popup msg when clicking about button
        aboutbtn.setOnClickListener {
            var dialog = Popup()
            dialog.show(supportFragmentManager, "customDialog")
        }
        // start new game
        newgamebtn.setOnClickListener {
            val newGameIntent = Intent(this, GameScreen::class.java)
            startActivity(newGameIntent)
        }
    }
}