package com.julia.apd.chuckie.module

import com.julia.apd.chuckie.remote.ChuckNorrisApi
import com.julia.apd.chuckie.dao.ChuckNorrisImpl
import com.julia.apd.chuckie.dao.ChuckNorrisRepository
import com.julia.apd.chuckie.networking.HttpClient
import com.julia.apd.chuckie.networking.ResponseHandler
import com.julia.apd.chuckie.networking.RetrofitFactory
import org.koin.dsl.module

val chuckNorrisServerModules = module {
    single { HttpClient.createHttpClient() }
    single { ResponseHandler() }
    single { RetrofitFactory.create(ChuckNorrisApi::class.java, ChuckNorrisApi.url, okHttpClient = get()) }
    single<ChuckNorrisRepository> {
        ChuckNorrisImpl(
            jokeServiceApi = get(),
            responseHandler = get()
        )
    }
}



