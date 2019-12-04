package com.julia.apd.chuckie.module

import com.julia.apd.chuckie.remote.ChuckNorrisApi
import com.julia.apd.chuckie.dao.ChuckNorrisImpl
import com.julia.apd.chuckie.dao.ChuckNorrisRepository
import com.julia.apd.chuckie.networking.HttpClient
import com.julia.apd.chuckie.networking.ResponseHandler
import org.koin.dsl.module

val chuckNorrisServerModules = module {
    single { HttpClient.createHttpClient() }
    factory { ResponseHandler() }
    single { ChuckNorrisApi.create(okHttpClient = get()) }
    factory<ChuckNorrisRepository> {
        ChuckNorrisImpl(
            jokeServiceApi = get(),
            responseHandler = get()
        )
    }
}



