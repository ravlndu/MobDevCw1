package com.example.mobdevcw1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer

import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class GameScreen : AppCompatActivity() {
    @SuppressLint("SetTextI18n")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)
        var currentTime = 50
        val timeInMills: Long = 300000
        var tempScore = 0
        var finalWins = 0
        var finalLost = 0

        val expressionLeft = findViewById<TextView>(R.id.exp1)
        val expressionRight = findViewById<TextView>(R.id.exp2)
        val resultTxt = findViewById<TextView>(R.id.result)
        val clock = findViewById<TextView>(R.id.clock)
        val gameScoreText = findViewById<TextView>(R.id.scoreTxt)
        val greaterBTN = findViewById<Button>(R.id.greaterBtn)
        val lessBTN = findViewById<Button>(R.id.lessBtn)
        val equalBTN = findViewById<Button>(R.id.equalBtn)


        var finalQuestionLeft = FinalExpression()
        var finalQuestionRight = FinalExpression()

        finalQuestionLeft.finalQuestion()
        finalQuestionRight.finalQuestion()
        //println("total left -- ${finalQuestionLeft.total()}")
        //println("total right -- ${finalQuestionRight.total()}")
        expressionLeft.text = finalQuestionLeft.questionStr()
        expressionRight.text = finalQuestionRight.questionStr()
        val intent = Intent(this, GameResult::class.java)

        // countdown timer
        val timer = object : CountDownTimer(timeInMills, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTime--
                clock.text = "$currentTime"
                if (currentTime == 0) {
                    cancel()
                    intent.putExtra("FINAL_WINS", finalWins.toString())
                    intent.putExtra("FINAL_LOST", finalLost.toString())
                    startActivity(intent)
                }
            }

            override fun onFinish() {

            }
        }
        timer.start()

        greaterBTN.setOnClickListener {
            if (finalQuestionLeft.total() > finalQuestionRight.total()) {
                resultTxt.text = "Correct!"
                resultTxt.setTextColor(Color.GREEN)
                tempScore++
                finalWins++
                gameScoreText.text = "$finalWins"

                //increment time after 5 wins
                if (tempScore == 5) {
                    currentTime += 10
                    tempScore = 0
                    clock.text = "$currentTime"
                }

            } else {
                resultTxt.text = "Wrong!"
                resultTxt.setTextColor(Color.RED)
                finalLost++

            }
            finalQuestionLeft = FinalExpression()
            finalQuestionRight = FinalExpression()
            finalQuestionLeft.finalQuestion()
            finalQuestionRight.finalQuestion()
            //println("total left -- ${finalQuestionLeft.total()}")
            //println("total right -- ${finalQuestionRight.total()}")
            expressionLeft.text = finalQuestionLeft.questionStr()
            expressionRight.text = finalQuestionRight.questionStr()

        }
        lessBTN.setOnClickListener {
            if (finalQuestionLeft.total() < finalQuestionRight.total()) {
                resultTxt.text = "Correct!"
                resultTxt.setTextColor(Color.GREEN)
                tempScore++
                finalWins++
                gameScoreText.text = "$finalWins"
                if (tempScore == 5) {
                    currentTime += 10
                    tempScore = 0
                    clock.text = "$currentTime"
                }


            } else {
                resultTxt.text = "Wrong!"
                resultTxt.setTextColor(Color.RED)
                finalLost++

            }
            finalQuestionLeft = FinalExpression()
            finalQuestionRight = FinalExpression()
            finalQuestionLeft.finalQuestion()
            finalQuestionRight.finalQuestion()
            //println("total left -- ${finalQuestionLeft.total()}")
            //println("total right -- ${finalQuestionRight.total()}")
            expressionLeft.text = finalQuestionLeft.questionStr()
            expressionRight.text = finalQuestionRight.questionStr()


        }
        equalBTN.setOnClickListener {
            if (finalQuestionLeft.total() == finalQuestionRight.total()) {
                resultTxt.text = "Correct!"
                resultTxt.setTextColor(Color.GREEN)
                tempScore++
                finalWins++
                gameScoreText.text = "$finalWins"
                if (tempScore == 5) {
                    currentTime += 10
                    tempScore = 0
                    clock.text = "$currentTime"
                }

            } else {
                resultTxt.text = "Wrong!"
                resultTxt.setTextColor(Color.RED)
                finalLost++

            }
            finalQuestionLeft = FinalExpression()
            finalQuestionRight = FinalExpression()
            finalQuestionLeft.finalQuestion()
            finalQuestionRight.finalQuestion()
            //println("total left -- ${finalQuestionLeft.total()}")
            //println("total right -- ${finalQuestionRight.total()}")
            expressionLeft.text = finalQuestionLeft.questionStr()
            expressionRight.text = finalQuestionRight.questionStr()

        }

    }

}

class FinalExpression {
    private var qElement: Int = (1..20).random()
    private var termCount: Int = (1..4).random()
    private var total: Int = qElement
    private var questionString = ""
    private var newElement = PrimaryExpression()


    fun finalQuestion() {

        // Generating question with parentheses
        when (termCount) {
            1 -> {
                questionString = "$qElement"
                total = qElement
            }
            2 -> {
                questionString = "$qElement"
                total = newElement.primaryQuestionTotal(total)
                questionString += newElement.primaryQuestionString()
                total = newElement.primaryQuestionTotal(total)

            }
            3 -> {
                questionString = "$qElement"
                total = newElement.primaryQuestionTotal(total)
                questionString = "($qElement ${newElement.primaryQuestionString()})"
                total = newElement.primaryQuestionTotal(total)
                questionString += newElement.primaryQuestionString()
                total = newElement.primaryQuestionTotal(total)
            }
            4 -> {
                questionString = "$qElement"
                total = newElement.primaryQuestionTotal(total)
                questionString = "(($qElement ${newElement.primaryQuestionString()})"
                total = newElement.primaryQuestionTotal(total)
                questionString += "${newElement.primaryQuestionString()})"
                total = newElement.primaryQuestionTotal(total)
                questionString += newElement.primaryQuestionString()
                total = newElement.primaryQuestionTotal(total)
            }
        }
    }

    fun total(): Int {
        return total
    }

    fun questionStr(): String {
        return questionString
    }


}

class PrimaryExpression {
    private var total: Int = 0
    private var questionString = ""
    private var operators = arrayOf("+", "-", "*", "/")
    private var operatorStr = ""

    private var qElement: Int = 0

    fun primaryQuestionString(): String {
        operatorStr = operators.random()
        val list = (1..20)

        //extract factors to divide
        if (operatorStr == "/") {
            val factors = list.shuffled()
            for (i in factors) {
                if (total % i == 0) {
                    qElement = i
                }
            }
        //limit total not to exceed 100
        } else if (operatorStr == "+" || operatorStr == "*") {
            val values = list.shuffled()
            for (i in values) {
                if (total + i < 101 && total * i < 101) {
                    qElement = i
                    break
                }
            }

        } else {
            qElement = list.random()
        }
        questionString = "$operatorStr $qElement"
        return questionString
    }

    fun primaryQuestionTotal(finalTotal: Int): Int {
        total = finalTotal
        when (operatorStr) {
            "+" -> total += qElement
            "-" -> total -= qElement
            "*" -> total *= qElement
            "/" -> total /= qElement

        }

        return total
    }


}