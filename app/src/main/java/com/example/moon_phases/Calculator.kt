package com.example.moon_phases

import com.example.moon_phases.Algorithms.Methods.simple
import com.example.moon_phases.Algorithms.Methods.conway
import com.example.moon_phases.Algorithms.Methods.trig1
import com.example.moon_phases.Algorithms.Methods.trig2
import java.util.*


class Calculator {
    object Calcs{
        private val currentDate: Calendar = Calendar.getInstance()
        private val day: Int = currentDate.get(Calendar.DAY_OF_MONTH)
        private var month: Int = currentDate.get(Calendar.MONTH)
        private val year: Int = currentDate.get(Calendar.YEAR)

        fun calcPhase(algorithm: String):Int{
            var phase = 0

            if (algorithm == "simple") {
                phase = simple(year, month, day)
            }else if (algorithm == "conway"){
                phase = conway(year, month, day)
            }else if (algorithm == "trig1"){
                phase = trig1(year, month, day)
            }else if (algorithm == "trig2"){
                phase = trig2(year, month, day)
            }
            return phase
        }

        private fun checkNextDay(i:Int):Calendar{
            val dayToCheck = Calendar.getInstance()
            dayToCheck.add(Calendar.DAY_OF_YEAR, i)
            return dayToCheck
        }

        fun calcNextFull(algorithm: String): Calendar{
            var i = 1
            var isfull = 0
            var checkThisDay : Calendar
            do{
                checkThisDay = checkNextDay(i)
                val year = checkThisDay.get(Calendar.YEAR)
                val month = checkThisDay.get(Calendar.MONTH)
                val day = checkThisDay.get(Calendar.DAY_OF_MONTH)
                if (algorithm == "simple") {
                    isfull = simple(year, month, day)
                }else if (algorithm == "conway"){
                    isfull = conway(year, month, day)
                }else if (algorithm == "trig1"){
                    isfull = trig1(year, month, day)
                }else if (algorithm == "trig2"){
                    isfull = trig2(year, month, day)
                }
                i++
            }while(isfull != 15)
            return checkThisDay
        }

        fun calcPrevNewm(algorithm: String):Calendar{
            var i = -1
            var isnewmoon = 0
            var checkThisDay: Calendar
            do{
                checkThisDay = checkNextDay(i)
                val year = checkThisDay.get(Calendar.YEAR)
                val month = checkThisDay.get(Calendar.MONTH)
                val day = checkThisDay.get(Calendar.DAY_OF_MONTH)
                if (algorithm == "simple") {
                    isnewmoon = simple(year, month, day)
                }else if (algorithm == "conway"){
                    isnewmoon = conway(year, month, day)
                }else if (algorithm == "trig1"){
                    isnewmoon = trig1(year, month, day)
                }else if (algorithm == "trig2"){
                    isnewmoon = trig2(year, month, day)
                }
                i--
            }while(isnewmoon != 0)
            return checkThisDay
        }

        fun calcFullMoons(year:Int, algorithm: String) : MutableList<String>{
            var isfull = 0
            var date = ""
            val list = mutableListOf<String>()
            val checkThisDay: Calendar = Calendar.getInstance()
            for(month in 1..12){
                checkThisDay.set(year, month, 1)
                val days= checkThisDay.getActualMaximum(Calendar.DAY_OF_MONTH)
                for(day in 1..days){
                    checkThisDay.set(year, month, day)
                    if (algorithm == "simple") {
                        isfull = simple(year, month, day)
                    }else if (algorithm == "conway"){
                        isfull = conway(year, month, day)
                    }else if (algorithm == "trig1"){
                        isfull = trig1(year, month, day)
                    }else if (algorithm == "trig2"){
                        isfull = trig2(year, month, day)
                    }
                    if(isfull == 15){
                        date = "${day}.${month}.${year}"
                        list.add(date)
                    }

                }
            }
            return list
        }
    }
}