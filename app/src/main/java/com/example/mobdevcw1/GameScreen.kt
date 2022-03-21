package com.example.mobdevcw1

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle

import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class GameScreen : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        val expressionLeft = findViewById<TextView>(R.id.exp1)
        val expressionRight = findViewById<TextView>(R.id.exp2)
        val resultTxt = findViewById<TextView>(R.id.result)
        val clock = findViewById<TextView>(R.id.clock)
        val greaterBTN = findViewById<Button>(R.id.greaterBtn)
        val lessBTN = findViewById<Button>(R.id.lessBtn)
        val equalBTN =findViewById<Button>(R.id.equalBtn)




        var finalQuestionLeft = FinalExpression()
        var finalQuestionRight = FinalExpression()

        finalQuestionLeft.finalQuestion()
        finalQuestionRight.finalQuestion()
        println("total left -- ${finalQuestionLeft.total()}")
        println("total right -- ${finalQuestionRight.total()}")
        expressionLeft.text = finalQuestionLeft.questionStr()
        expressionRight.text = finalQuestionRight.questionStr()





        greaterBTN.setOnClickListener{
            if (finalQuestionLeft.total()>finalQuestionRight.total()){
                resultTxt.text = "Correct!"
                resultTxt.setTextColor(Color.GREEN)

            }else{
                resultTxt.text = "Wrong!"
                resultTxt.setTextColor(Color.RED)

            }
            finalQuestionLeft = FinalExpression()
            finalQuestionRight = FinalExpression()
            finalQuestionLeft.finalQuestion()
            finalQuestionRight.finalQuestion()
            println("total left -- ${finalQuestionLeft.total()}")
            println("total right -- ${finalQuestionRight.total()}")
            expressionLeft.text = finalQuestionLeft.questionStr()
            expressionRight.text = finalQuestionRight.questionStr()

        }
        lessBTN.setOnClickListener{
            if (finalQuestionLeft.total()<finalQuestionRight.total()){
                resultTxt.text = "Correct!"
                resultTxt.setTextColor(Color.GREEN)

            }else{
                resultTxt.text = "Wrong!"
                resultTxt.setTextColor(Color.RED)

            }
            finalQuestionLeft = FinalExpression()
            finalQuestionRight = FinalExpression()
            finalQuestionLeft.finalQuestion()
            finalQuestionRight.finalQuestion()
            println("total left -- ${finalQuestionLeft.total()}")
            println("total right -- ${finalQuestionRight.total()}")
            expressionLeft.text = finalQuestionLeft.questionStr()
            expressionRight.text = finalQuestionRight.questionStr()


        }
        equalBTN.setOnClickListener{
            if (finalQuestionLeft.total()==finalQuestionRight.total()){
                resultTxt.text = "Correct!"
                resultTxt.setTextColor(Color.GREEN)

            }else{
                resultTxt.text = "Wrong!"
                resultTxt.setTextColor(Color.RED)

            }
            finalQuestionLeft = FinalExpression()
            finalQuestionRight = FinalExpression()
            finalQuestionLeft.finalQuestion()
            finalQuestionRight.finalQuestion()
            println("total left -- ${finalQuestionLeft.total()}")
            println("total right -- ${finalQuestionRight.total()}")
            expressionLeft.text = finalQuestionLeft.questionStr()
            expressionRight.text = finalQuestionRight.questionStr()


        }

    }


}
class FinalExpression{
    private var qElement:Int = (1..20).random()
    private var termCount:Int = (1..4).random()
    private var total:Int = qElement
    private var questionString = ""
    private var newElement = PrimaryExpression()


    fun finalQuestion(){

        when(termCount){
            1 -> {
                questionString = "$qElement"
                total = qElement
            }
            2 ->{
                questionString = "$qElement"
                total = newElement.primaryQuestionTotal(total)
                questionString += newElement.primaryQuestionString()
                total = newElement.primaryQuestionTotal(total)

            }
            3 ->{
                questionString = "$qElement"
                total = newElement.primaryQuestionTotal(total)
                questionString = "($qElement ${newElement.primaryQuestionString()})"
                total = newElement.primaryQuestionTotal(total)
                questionString += newElement.primaryQuestionString()
                total = newElement.primaryQuestionTotal(total)
            }
            4 ->{
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

class PrimaryExpression{
    private var total:Int =0
    private var questionString = ""
    private var operators= arrayOf("+","-","*","/")
    private var operatorStr =""

    private var qElement:Int =0

    fun primaryQuestionString(): String {
        operatorStr=operators.random()
        val list = (1..20)

        if (operatorStr == "/"){
            val factors = list.shuffled()
            for(i in factors){
                if(total%i==0){
                    qElement= i
                }
            }
        }else if (operatorStr == "+" || operatorStr == "*"){
            val values = list.shuffled()
            for(i in values){
                if(total+i<101 && total *i<101){
                    qElement = i
                    break
                }
            }

        } else {
            qElement = list.random()
        }
        questionString= "$operatorStr $qElement"
        return questionString
    }
    fun primaryQuestionTotal(finalTotal:Int): Int {
        total = finalTotal
        when(operatorStr){
            "+" -> total += qElement
            "-" -> total -= qElement
            "*" -> total *= qElement
            "/" -> total /= qElement

        }

        return total
    }






}