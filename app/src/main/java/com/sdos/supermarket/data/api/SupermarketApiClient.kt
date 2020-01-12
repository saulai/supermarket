package com.sdos.supermarket.data.api

import com.sdos.supermarket.BuildConfig
import com.sdos.supermarket.common.Either
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ProductsApiClient{

    private var service: ProductsApi

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(httpLoggingInterceptor)
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        clientBuilder.readTimeout(30, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS)


        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.PRODUCTS_API_URL)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ProductsApi::class.java)
    }


    fun getProduct(category: String, item: String) = callService {
        service.getProduct(category, item)
    }


    private fun <T> callService(callback: () -> Call<T>): Either<T, Throwable> {
        return try {
            val response = callback().execute()
            when {
                response.isSuccessful -> {
                    response.body()?.let {
                        Either.Success(it)
                    } ?: Either.Error(Throwable(response.message()))
                }
                else -> Either.Error(Throwable(response.message()))
            }
        } catch (exception: Exception) {
            return Either.Error(exception)
        }
    }
}
