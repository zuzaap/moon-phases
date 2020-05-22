package com.example.moon_phases

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import com.example.moon_phases.Calculator.Calcs.calcNextFull
import com.example.moon_phases.Calculator.Calcs.calcPrevNewm
import com.example.moon_phases.Calculator.Calcs.calcPhase
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initState()
    }

    override fun onResume() {
        super.onResume()
        initState()
    }

    @SuppressLint("SetTextI18n")
    private fun initState(){

        var prefs = PreferenceManager.getDefaultSharedPreferences(this)
        var algorithm = prefs.getString("algorithm", "trig1")
        var hemisphere = prefs.getString("hemisphere", "S")

        val today:TextView = findViewById(R.id.moon_today)
        val todayPhase = calcPhase(algorithm)
        val percentTodayPhase = ((todayPhase.toDouble()/30) * 100).roundToInt()
        today.text = "Dzisiaj: ${percentTodayPhase}%"

        val prev:TextView = findViewById(R.id.prev_new_moon)
        val prevNewMoon = calcPrevNewm(algorithm)
        val prevDay = prevNewMoon.get(Calendar.DAY_OF_MONTH)
        val prevMonth = prevNewMoon.get(Calendar.MONTH)
        val prevYear = prevNewMoon.get(Calendar.YEAR)
        prev.text = "Poprzedni nów: ${prevDay}.${prevMonth}.${prevYear} "

        val next:TextView = findViewById(R.id.next_full)
        val nextFullMoon = calcNextFull(algorithm)
        val nextDay = nextFullMoon.get(Calendar.DAY_OF_MONTH)
        val nextMonth = nextFullMoon.get(Calendar.MONTH)
        val nextYear = nextFullMoon.get(Calendar.YEAR)
        next.text = "Następna pełnia: ${nextDay}.${nextMonth}.${nextYear}"


        //photo
        val moonImage : ImageView = findViewById(R.id.moon_image)
        val imageId = setImage(todayPhase, hemisphere)
        val resourceId = resources.getIdentifier(imageId, "drawable", packageName)
        moonImage.setImageResource(resourceId)


        //buttons
        val showAllYear : Button = findViewById(R.id.show_all_year)
        showAllYear.setOnClickListener{
            val intent = Intent(this, YearActivity::class.java)
            startActivity(intent)
        }

        val settings : Button = findViewById(R.id.show_settings)
        settings.setOnClickListener{
            val intent = Intent( this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setImage(phase:Int, hem:String):String{
        val filename : String
        var hemisphere = hem.toLowerCase(Locale.ROOT)
        if(phase == 0) {
            filename = hemisphere + "30"
        }else{
            filename = hemisphere + "${phase}"
        }
        return filename
    }
}
