package br.com.regmoraes.shufflesongs.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 *
 *   The configuration of the network stack using the Retrofit library
 **/
internal object RetrofitConfiguration {

    private val originalBaseUrl by lazy {
        "https://us-central1-tw-exercicio-mobile.cloudfunctions.net/"
    }

    var baseUrl = originalBaseUrl

    val gson: Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd")
        .create()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    fun buildRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }


    fun mapErrorResponse(errorBody: ResponseBody?): ApiError {

        val bodyAsString = errorBody?.string()

        return try {
            gson.fromJson(bodyAsString, ApiError::class.java)

        } catch (e: Throwable) {
            ApiError(e.message ?: "Unknown error")
        }
    }
}