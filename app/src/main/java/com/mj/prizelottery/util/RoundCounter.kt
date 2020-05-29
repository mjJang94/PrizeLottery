package com.mj.prizelottery.util

import java.time.LocalDate
import java.util.*

open class RoundCounter {

    // Millisecond 하루(24 시간)
    private val ONE_DAY = 24 * 60 * 60 * 1000

    fun getDday(
        a_year: Int,
        a_monthOfYear: Int,
        a_dayOfMonth: Int
    ): String { // D-day 설정
        val ddayCalendar: Calendar = Calendar.getInstance()
        ddayCalendar.set(a_year, a_monthOfYear, a_dayOfMonth)

        val dday: Long = (ddayCalendar.getTimeInMillis() / ONE_DAY)

        val today: Long = Calendar.getInstance().getTimeInMillis() / ONE_DAY
        var result = today - dday

        result = (result/7) + 1


        return result.toString()
    }
}