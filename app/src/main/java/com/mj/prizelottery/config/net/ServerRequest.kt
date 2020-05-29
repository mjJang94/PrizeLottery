package com.mj.prizelottery.config.net

import android.os.Handler
import android.os.Message
import com.mj.prizelottery.config.Constant
import com.mj.prizelottery.vo.RecentRoundData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerRequest {


    fun getRoundNum(
        recentRound: String,
        handler: Handler, success: Int,
        fail: Int
    ) {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gitHurbService: RetrofitConnection =
            retrofit.create<RetrofitConnection>(RetrofitConnection::class.java)

        val call: Call<RecentRoundData> =
            gitHurbService.getLotto(Constant.QUERY_METHOD, recentRound)
        call.enqueue(object : Callback<RecentRoundData?> {
            override fun onResponse(
                call: Call<RecentRoundData?>,
                response: Response<RecentRoundData?>
            ) {
                if (response.isSuccessful()) {
                    handler.sendMessage(
                        Message.obtain(
                            handler,
                            success,
                            response.body()
                        )
                    )
                } else {
                    handler.sendMessage(Message.obtain(handler, fail, response.body()))
                }
            }

            override fun onFailure(call: Call<RecentRoundData?>, t: Throwable) {
                t.printStackTrace()
                handler.sendMessage(Message.obtain(handler, Constant.RESPONSE_FAIL))
            }
        })
    }
}