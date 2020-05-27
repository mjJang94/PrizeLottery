package com.mj.prizelottery

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mj.prizelottery.util.DayCounter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity() : AppCompatActivity(), View.OnClickListener {

    var dayCounter: DayCounter? = null
    var mCalendar: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dayCounter = DayCounter()
        mCalendar = GregorianCalendar()

        txt_button.setOnClickListener(this)



    }

//    1회차 로또 번호
//    a_year = 2002
//    a_monthOfYear = 11
//    a_dayOfMonth = 7

    private val mDateSetListener = OnDateSetListener { a_view, a_year, a_monthOfYear, a_dayOfMonth ->
        // D-day 계산 결과 출력

    }



//    private fun requestLottery(userId: String, userPw: String) {
//
//        val loginDataVo = LoginDataVo()
//        loginDataVo.userId = userId
//        loginDataVo.userPw = userPw
//
//        val url: String =
//                NetConstant.BASE_URL + NetConstant.CATE_CLIENT
//        VolleyRequest<LoginDataVo>()
//                .reqPostByVo(applicationContext, url
//                        , Response.Listener { response ->
//                    run {
//
//                        //성공
//                    }
//                }
//                        , Response.ErrorListener { error ->
//                    run {
//
//                        //실패
//                    }
//                }, loginDataVo, false
//                )
//    }
//}

    override fun onClick(view: View) {
        when(view.id){
            R.id.txt_button -> {
                val year: Int = mCalendar!!.get(Calendar.YEAR)
                val month: Int = mCalendar!!.get(Calendar.MONTH)
                val day: Int = mCalendar!!.get(Calendar.DAY_OF_MONTH)

//                val dialog = DatePickerDialog(this, mDateSetListener, year, month, day)
//                dialog.show()

                Log.e("asdasd", dayCounter?.getDday(2002, 11, 7))
            }
        }
    }

}
