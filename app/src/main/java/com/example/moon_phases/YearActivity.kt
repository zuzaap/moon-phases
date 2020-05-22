package com.example.moon_phases

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.moon_phases.Calculator.Calcs.calcFullMoons
import kotlinx.android.synthetic.main.acitivity_year.*
import kotlin.collections.ArrayList

class YearActivity : AppCompatActivity() {

    private var result: MutableList<String> = ArrayList()
    private var algorithm = "simple"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_year)
        val input1 : EditText = findViewById(R.id.editText)
        input1.setText("2020")
        actions()
    }

    private fun actions(){
        var input = editText.text.toString().toInt()

        val count : Button = findViewById(R.id.count_start)
        count.setOnClickListener {
            result.clear()
            checkYear(input)
        }
        val plus : Button = findViewById(R.id.plus)
        plus.setOnClickListener {
            input ++
            editText.setText("$input")
        }
        val minus : Button = findViewById(R.id.minus)
        minus.setOnClickListener {
            input --
            editText.setText("$input")
        }

    }

    private fun checkYear(input : Int){
        if ( input > 1899 || input < 2201){
            showAllPhases(input)
        }else{
            Toast.makeText(applicationContext, "Ustaw datę między rokiem 1900 a 2200", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showAllPhases(year:Int){
        result = calcFullMoons(year, algorithm)
        val adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, result)
        this.list_of_fulls.adapter = adapter
    }
}