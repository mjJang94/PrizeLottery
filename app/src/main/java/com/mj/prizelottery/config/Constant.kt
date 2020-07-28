package com.mj.prizelottery.config

interface Constant {

    companion object {

        /**
         * URL
         */
        val BASE_URL = "https://www.dhlottery.co.kr"


        /**
         * 최초 로또 시작일
         */
        const val FIRST_ROUND_YEAR = 2002
        const val FIRST_ROUND_MONTH = 11 //12월인데 Calender 고려
        const val FIRST_ROUND_DAY = 7

        /**
         * 로또 번호 조회 필수 쿼리 메소드
         */
        val QUERY_METHOD = "getLottoNumber"


        /**
         * (공통) 통신 실패
         */
        const val RESPONSE_FAIL = 400

        /**
         * 최근 회차 당첨 번호 조회 SUC/ FAIL
         */
        const val RESPONSE_SUC_GET_NUM = 201
        const val RESPONSE_FAIL_GET_NUM = 401
    }
}