package com.example.mobdevcw1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GameResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_result)

        //displaying game result
        val winsTxt = findViewById<TextView>(R.id.total_correct_text)
        val lostTxt = findViewById<TextView>(R.id.total_wrong_txt)
        val totalWins = intent.getStringExtra("FINAL_WINS")
        val totalLost = intent.getStringExtra("FINAL_LOST")
        winsTxt.text = "$totalWins"
        lostTxt.text = "$totalLost"

    }

    override fun onBackPressed() {
        super.onBackPressed()
        // back to main menu after pressing back button
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}