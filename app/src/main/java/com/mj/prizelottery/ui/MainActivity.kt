package com.mj.prizelottery.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mj.prizelottery.R
import com.mj.prizelottery.config.Constant
import com.mj.prizelottery.config.net.ServerRequest
import com.mj.prizelottery.util.RoundCounter
import com.mj.prizelottery.vo.RecentRoundData
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity() : AppCompatActivity(), View.OnClickListener {

    val RED = "#ff312f"
    val serverRequest = ServerRequest()
    var roundCounter: RoundCounter? = null
    var mCalendar: Calendar? = null

    @SuppressLint("HandlerLeak")
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                Constant.RESPONSE_SUC_GET_NUM -> {
                    val recentRoundData = msg.obj as RecentRoundData
                    setRoundData(recentRoundData)
                }
                Constant.RESPONSE_FAIL_GET_NUM -> {
                    Toast.makeText(this@MainActivity, "정보 불러오기에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }

                Constant.RESPONSE_FAIL -> {
                    Toast.makeText(this@MainActivity, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        getRecentLottoData()

    }

    override fun onClick(view: View) {
        when (view.id) {

        }
    }

    private fun init() {
        roundCounter = RoundCounter()
        mCalendar = GregorianCalendar()
    }

    private fun setRoundData(recentRoundData: RecentRoundData) {

        val drwNo = recentRoundData.drwNo + getString(R.string.common_round)
        val startIndex = 0
        val endIndex = drwNo.length

        val ssb = SpannableStringBuilder(drwNo + getString(R.string.common_win_number))
        ssb.setSpan(
            ForegroundColorSpan(Color.parseColor(RED)),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        txt_round.text = ssb
        txt_date.text = recentRoundData.drwNoDate

        setNumDataAndColor(recentRoundData)

    }

    private fun setNumDataAndColor(recentRoundData: RecentRoundData){
        txt_drwtNo1.text = recentRoundData.drwtNo1
        txt_drwtNo1.background = sortingByNum(recentRoundData.drwtNo1)

        txt_drwtNo2.text = recentRoundData.drwtNo2
        txt_drwtNo2.background = sortingByNum(recentRoundData.drwtNo2)

        txt_drwtNo3.text = recentRoundData.drwtNo3
        txt_drwtNo3.background = sortingByNum(recentRoundData.drwtNo3)

        txt_drwtNo4.text = recentRoundData.drwtNo4
        txt_drwtNo4.background = sortingByNum(recentRoundData.drwtNo4)

        txt_drwtNo5.text = recentRoundData.drwtNo5
        txt_drwtNo5.background = sortingByNum(recentRoundData.drwtNo5)

        txt_drwtNo6.text = recentRoundData.drwtNo6
        txt_drwtNo6.background = sortingByNum(recentRoundData.drwtNo6)

        txt_bnusNo.text = recentRoundData.bnusNo
        txt_bnusNo.background = sortingByNum(recentRoundData.bnusNo)
    }



    private fun sortingByNum(value: String): Drawable? {

        var background : Drawable ?= null

        when (value.toInt()) {
            in 1..10 -> {
                background = resources.getDrawable(R.drawable.circle_background_yellow, null)
            }
            in 11..20 -> {
                background = resources.getDrawable(R.drawable.circle_background_blue, null)
            }
            in 21..30 -> {
                background = resources.getDrawable(R.drawable.circle_background_red, null)
            }
            in 31..40 -> {
                background = resources.getDrawable(R.drawable.circle_background_purple, null)
            }
            in 41..45 -> {
                background = resources.getDrawable(R.drawable.circle_background_grey, null)
            }
        }

        return background
    }

    private fun getRecentLottoData() {

        val recentRoundNumber = roundCounter!!.getDday(
            Constant.FIRST_ROUND_YEAR,
            Constant.FIRST_ROUND_MONTH,
            Constant.FIRST_ROUND_DAY
        )

        serverRequest.getRoundNum(
            recentRoundNumber,
            mHandler,
            Constant.RESPONSE_SUC_GET_NUM,
            Constant.RESPONSE_FAIL_GET_NUM
        )
    }
}
