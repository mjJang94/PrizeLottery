package com.mj.prizelottery.koin

import com.google.gson.GsonBuilder
import com.mj.prizelottery.config.Constant
import com.mj.prizelottery.config.net.RetrofitConnection
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 *1) module - Koin모듈을 정의할때 사용
 *2) factory - Dagger에서의 ActivityScope, FragmentScope와 유사한 기능으로 inject하는 시점에 해당 객체를 생성
 *3) single - Dagger에서의 Singleton 과 동일하며 앱이 살아있는 동안 전역적으로 사용가능한 객체를 생성
 *4) bind - 생성할 객체를 다른 타입으로 바인딩하고 싶을때 사용
 *5) get - 주입할 각 컴포넌트끼리의 의존성을 해결하기 위해 사용합니다.

 */

fun netWorkModule() = module {

    single {
        GsonBuilder().setLenient().create()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(get())) //get()은 같은 module{} 내부에 있는 single로 선언된 객체를 자동으로 알아서 알맞게 가져와서 사용한다.
            .baseUrl(Constant.BASE_URL)
            .build()
            .create(RetrofitConnection::class.java)
    }
}


