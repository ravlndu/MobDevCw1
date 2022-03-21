package com.example.mobdevcw1

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class GameScreen : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    var gamestarted = false
    lateinit var countDownTimer: CountDownTimer
    var initialCountDown: Long=50000
    var countdownInterval: Long=1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        val expression_left = findViewById<TextView>(R.id.exp1)
        val expression_right = findViewById<TextView>(R.id.exp2)
        val resultTxt = findViewById<TextView>(R.id.result)
        val clock = findViewById<TextView>(R.id.clock)
        val greaterBTN = findViewById<Button>(R.id.greaterBtn)
        val lessBTN = findViewById<Button>(R.id.lessBtn)
        val equalBTN =findViewById<Button>(R.id.equalBtn)




        var finalquestionleft = FinalExpression()
        var finalquestionright = FinalExpression()

        finalquestionleft.FinalQuestion()
        finalquestionright.FinalQuestion()
        println("total left -- ${finalquestionleft.total()}")
        println("total right -- ${finalquestionright.total()}")
        expression_left.text = finalquestionleft.questionstr()
        expression_right.text = finalquestionright.questionstr()





        greaterBTN.setOnClickListener{
            if (finalquestionleft.total()>finalquestionright.total()){
                resultTxt.text = "Correct!"
                resultTxt.setTextColor(Color.GREEN)

            }else{
                resultTxt.text = "Wrong!"
                resultTxt.setTextColor(Color.RED)

            }
            finalquestionleft = FinalExpression()
            finalquestionright = FinalExpression()
            finalquestionleft.FinalQuestion()
            finalquestionright.FinalQuestion()
            println("total left -- ${finalquestionleft.total()}")
            println("total right -- ${finalquestionright.total()}")
            expression_left.text = finalquestionleft.questionstr()
            expression_right.text = finalquestionright.questionstr()

        }
        lessBTN.setOnClickListener{
            if (finalquestionleft.total()<finalquestionright.total()){
                resultTxt.text = "Correct!"
                resultTxt.setTextColor(Color.GREEN)

            }else{
                resultTxt.text = "Wrong!"
                resultTxt.setTextColor(Color.RED)

            }
            finalquestionleft = FinalExpression()
            finalquestionright = FinalExpression()
            finalquestionleft.FinalQuestion()
            finalquestionright.FinalQuestion()
            println("total left -- ${finalquestionleft.total()}")
            println("total right -- ${finalquestionright.total()}")
            expression_left.text = finalquestionleft.questionstr()
            expression_right.text = finalquestionright.questionstr()


        }
        equalBTN.setOnClickListener{
            if (finalquestionleft.total()==finalquestionright.total()){
                resultTxt.text = "Correct!"
                resultTxt.setTextColor(Color.GREEN)

            }else{
                resultTxt.text = "Wrong!"
                resultTxt.setTextColor(Color.RED)

            }
            finalquestionleft = FinalExpression()
            finalquestionright = FinalExpression()
            finalquestionleft.FinalQuestion()
            finalquestionright.FinalQuestion()
            println("total left -- ${finalquestionleft.total()}")
            println("total right -- ${finalquestionright.total()}")
            expression_left.text = finalquestionleft.questionstr()
            expression_right.text = finalquestionright.questionstr()


        }

    }


}
class FinalExpression{
    var qelement:Int = (1..20).random()
    var termcount:Int = (1..4).random()
    var total:Int = qelement
    var question_str = ""
    var newelement = PrimaryExpression()


    fun FinalQuestion(){

        when(termcount){
            1 -> {
                question_str = "$qelement"
                total = qelement
            }
            2 ->{
                question_str = "$qelement"
                total = newelement.primary_questiontotal(total)
                question_str = question_str+ newelement.primary_questionstr()
                total = newelement.primary_questiontotal(total)

            }
            3 ->{
                question_str = "$qelement"
                total = newelement.primary_questiontotal(total)
                question_str = "($qelement ${newelement.primary_questionstr()})"
                total = newelement.primary_questiontotal(total)
                question_str = question_str+ newelement.primary_questionstr()
                total = newelement.primary_questiontotal(total)
            }
            4 ->{
                question_str = "$qelement"
                total = newelement.primary_questiontotal(total)
                question_str = "(($qelement ${newelement.primary_questionstr()})"
                total = newelement.primary_questiontotal(total)
                question_str = question_str+"${newelement.primary_questionstr()})"
                total = newelement.primary_questiontotal(total)
                question_str = question_str+ newelement.primary_questionstr()
                total = newelement.primary_questiontotal(total)
            }
        }
    }

    fun total(): Int {
        return total
    }

    fun questionstr(): String {
        return question_str
    }


}

class PrimaryExpression{
    var total:Int =0
    var question_str = ""
    var operators= arrayOf("+","-","*","/")
    var operatorstr =""

    var qelement:Int =0

    fun primary_questionstr(): String {
        operatorstr=operators.random()
        val list = (1..20)

        if (operatorstr.equals("/")){
            val factors = list.shuffled()
            for(i in factors){
                if(total%i==0){
                    qelement= i
                }
            }
        }else if (operatorstr.equals("+") || operatorstr.equals("*")){
            val values = list.shuffled()
            for(i in values){
                if(total+i<101 && total *i<101){
                    qelement = i
                    break
                }
            }

        } else {
            qelement = list.random()
        }
        question_str= "$operatorstr $qelement"
        return question_str
    }
    fun primary_questiontotal(finaltotal:Int): Int {
        total = finaltotal
        when(operatorstr){
            "+" -> total=total+qelement
            "-" -> total=total-qelement
            "*" -> total=total*qelement
            "/" -> total=total/qelement

        }

        return total
    }






}