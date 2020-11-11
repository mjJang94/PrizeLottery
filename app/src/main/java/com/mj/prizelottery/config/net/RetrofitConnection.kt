package com.mj.prizelottery.config.net

import com.mj.prizelottery.vo.RecentRoundData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitConnection {

    /**
     * 로또 최근회차 당첨번호 조회
     */
    @GET("/common.do")
    fun getLotto(@Query("method") method: String?, @Query("drwNo") drwNum: String?): Call<RecentRoundData>
}