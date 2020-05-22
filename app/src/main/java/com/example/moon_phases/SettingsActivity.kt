package com.example.moon_phases

import androidx.preference.PreferenceManager
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private var hemisphere = "N"
    private var algorithm = "trig1"

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        saveSettings()
    }

    private fun saveSettings(){
        chosen_algorithm.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener {
                _, checkedId -> val alg: RadioButton = findViewById(checkedId)
                algorithm = alg.text.toString()
        })

        chosen_hemisphere.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener {
                _, checkedId -> val sph: RadioButton = findViewById(checkedId)
                hemisphere = sph.text.toString()
        })



        save_button.setOnClickListener{

            val prefs = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = prefs.edit()

            if(algorithm == "Trigonometry 1"){
                algorithm = "trig1"
                editor.putString("algorithm", algorithm)

            }else if(algorithm == "Trigonometry 2"){
                algorithm = "trig2"
                editor.putString("algorithm", algorithm)

            } else if(algorithm == "Simple"){
                algorithm = "simple"
                editor.putString("algorithm", algorithm)
            } else if(algorithm == "Conway"){
                algorithm = "conway"
                editor.putString("algorithm", algorithm)

            }
            editor.putString("hemisphere", hemisphere)
            editor.commit()
        }
    }
}