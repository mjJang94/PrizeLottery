package com.mj.prizelottery.util

import java.util.*

open class DayCounter {

    // Millisecond 형태의 하루(24 시간)
    private val ONE_DAY = 24 * 60 * 60 * 1000

    fun getDday(
        a_year: Int,
        a_monthOfYear: Int,
        a_dayOfMonth: Int
    ): String? { // D-day 설정
        val ddayCalendar: Calendar = Calendar.getInstance()
        ddayCalendar.set(a_year, a_monthOfYear, a_dayOfMonth)

        val dday: Long = ddayCalendar.getTimeInMillis() / ONE_DAY
        val today: Long = Calendar.getInstance().getTimeInMillis() / ONE_DAY
        var result = dday - today

        val strFormat: String
        if (result > 0) {
            strFormat = "D-%d"
        } else if (result == 0L) {
            strFormat = "D-Day"
        } else {
            result *= -1
            strFormat = "D+%d"
        }

        result /= 7

        return result.toString()
    }
}