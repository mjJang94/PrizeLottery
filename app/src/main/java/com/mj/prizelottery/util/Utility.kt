package com.mj.prizelottery.util

import android.content.Context
import android.graphics.drawable.Drawable
import com.mj.prizelottery.R
import java.util.*

open class Utility {

    /**
     * 로또의 금번 회차를 구하는 공식
     * @param a_year -- 로또가 처음 시작한 년도
     * @param a_monthOfYear -- 로또가 처음 시작한 달
     * @param a_dayOfMonth -- 로또가 처음 시작한 일
     * @return
     */
    fun getDday(
        a_year: Int,
        a_monthOfYear: Int,
        a_dayOfMonth: Int
    ): String { // D-day 설정

        val ONE_DAY = 24 * 60 * 60 * 1000

        val ddayCalendar: Calendar = Calendar.getInstance()
        ddayCalendar.set(a_year, a_monthOfYear, a_dayOfMonth)

        val dday: Long = (ddayCalendar.getTimeInMillis() / ONE_DAY)

        val today: Long = Calendar.getInstance().getTimeInMillis() / ONE_DAY
        var result = today - dday

        result = (result/7) + 1


        return result.toString()
    }


    /**
     * 로또 번호에 따른 공 색 구분
     */
    fun sortingByNum( context: Context, value: String): Drawable? {

        var background: Drawable? = null

        when (value.toInt()) {
            in 1..10 -> {
                background = context.resources.getDrawable(R.drawable.circle_background_yellow, null)
            }
            in 11..20 -> {
                background = context.resources.getDrawable(R.drawable.circle_background_blue, null)
            }
            in 21..30 -> {
                background = context.resources.getDrawable(R.drawable.circle_background_red, null)
            }
            in 31..40 -> {
                background = context.resources.getDrawable(R.drawable.circle_background_purple, null)
            }
            in 41..45 -> {
                background = context.resources.getDrawable(R.drawable.circle_background_grey, null)
            }
        }

        return background
    }

}