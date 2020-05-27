package com.mj.prizelottery

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mj.prizelottery.util.DayCounter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity() : AppCompatActivity(), View.OnClickListener {

    /**
     * 2002.12.7 일 부터 이미 로또는 1회차이다.
     */
    var dayCounter: DayCounter? = null
    var mCalendar: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dayCounter = DayCounter()
        mCalendar = GregorianCalendar()

        txt_button.setOnClickListener(this)



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
                txt_button.text = dayCounter?.getDday(2002, 11, 7)
            }
        }
    }

}
