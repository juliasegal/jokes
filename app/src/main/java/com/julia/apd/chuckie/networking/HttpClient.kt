package com.julia.apd.chuckie.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class HttpClient {
    companion object {
        fun createHttpClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
            client.readTimeout(5 * 60, TimeUnit.SECONDS)

            val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            client.addInterceptor(logging)
            return client.build()
        }
    }
}
