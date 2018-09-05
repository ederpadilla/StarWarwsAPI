package eder.padilla.dev.starwarssample.client

import com.google.gson.GsonBuilder

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import eder.padilla.dev.starwarssample.ui.Util

/**
 * Created by ederpadilla on 17/02/17.
 */

object ServiceGenerator {
    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)

    private val gson = GsonBuilder().create()


    private val builder = Retrofit.Builder()
            .baseUrl(Util.Z2V0QmFzZVVybA())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    val service: Client
        get() = createService(Client::class.java)

    fun <S> createService(serviceClass: Class<S>): S {
        return createService(serviceClass, null)
    }


    fun <S> createService(serviceClass: Class<S>, token: String?): S {
        httpClient.connectTimeout(90, TimeUnit.SECONDS)
        httpClient.readTimeout(90, TimeUnit.SECONDS)
        httpClient.writeTimeout(90, TimeUnit.SECONDS)
        val client = httpClient.build()
        val retrofit = builder.client(client).build()
        return retrofit.create(serviceClass)
    }

}