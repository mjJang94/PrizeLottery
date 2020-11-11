package com.mj.prizelottery.ui

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mj.prizelottery.R
import com.mj.prizelottery.config.Constant
import com.mj.prizelottery.config.net.RetrofitClient
import com.mj.prizelottery.config.net.RetrofitConnection
import com.mj.prizelottery.util.Utility
import com.mj.prizelottery.vo.RecentRoundData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener {

<<<<<<< HEAD
=======
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
>>>>>>> b5bc975c378df4c24eaa4af579bc0167e0c5906f

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
    }

    private fun setRoundData(recentRoundData: RecentRoundData) {

        val drwNo = recentRoundData.drwNo + getString(R.string.common_round)
        val startIndex = 0
        val endIndex = drwNo.length

        val ssb = SpannableStringBuilder(drwNo + getString(R.string.common_win_number))
        ssb.setSpan(
            ForegroundColorSpan(Color.parseColor("#ff312f")),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        txt_round.text = ssb
        txt_date.text = recentRoundData.drwNoDate

        setNumDataAndColor(recentRoundData)

    }

    private fun setNumDataAndColor(recentRoundData: RecentRoundData) {
        txt_drwtNo1.text = recentRoundData.drwtNo1
        txt_drwtNo1.background = Utility().sortingByNum(this, recentRoundData.drwtNo1)

        txt_drwtNo2.text = recentRoundData.drwtNo2
        txt_drwtNo2.background = Utility().sortingByNum(this, recentRoundData.drwtNo2)

        txt_drwtNo3.text = recentRoundData.drwtNo3
        txt_drwtNo3.background = Utility().sortingByNum(this, recentRoundData.drwtNo3)

        txt_drwtNo4.text = recentRoundData.drwtNo4
        txt_drwtNo4.background = Utility().sortingByNum(this, recentRoundData.drwtNo4)

        txt_drwtNo5.text = recentRoundData.drwtNo5
        txt_drwtNo5.background = Utility().sortingByNum(this, recentRoundData.drwtNo5)

        txt_drwtNo6.text = recentRoundData.drwtNo6
        txt_drwtNo6.background = Utility().sortingByNum(this, recentRoundData.drwtNo6)

        txt_bnusNo.text = recentRoundData.bnusNo
        txt_bnusNo.background = Utility().sortingByNum(this, recentRoundData.bnusNo)
    }

    /**
     * 오늘 기준 최근 회차 조회
     */
    private fun getRecentLottoData() {

        val recentRoundNumber = Utility().getDday(
            Constant.FIRST_ROUND_YEAR,
            Constant.FIRST_ROUND_MONTH,
            Constant.FIRST_ROUND_DAY
        )

        val retrofit = RetrofitClient().getInstance()
        retrofit.create(RetrofitConnection::class.java)
            .getLotto(Constant.QUERY_METHOD, recentRoundNumber)
            .enqueue(object : Callback<RecentRoundData?> {

                override fun onResponse(
                    call: Call<RecentRoundData?>,
                    response: Response<RecentRoundData?>
                ) {
                    val recentRoundData = response.body() as RecentRoundData
                    setRoundData(recentRoundData)
                }

                override fun onFailure(call: Call<RecentRoundData?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "정보 불러오기에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
