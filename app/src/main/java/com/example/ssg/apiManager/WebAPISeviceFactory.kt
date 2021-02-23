package com.example.ssg.apiManager


import com.example.ssg.AppClass
import com.readystatesoftware.chuck.BuildConfig
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class WebAPISeviceFactory {

    fun makeServiceFactory(): WebAPIService {
        return makeServiceFactory(makeOkHttpClient())
    }

    private fun makeServiceFactory(okHttpClient: OkHttpClient): WebAPIService {

        val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create(WebAPIService::class.java)
    }

    private fun makeOkHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient().newBuilder()
        httpClientBuilder.connectTimeout(HTTP_CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(HTTP_READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(HTTP_WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
         httpClientBuilder.addInterceptor(ChuckInterceptor(AppClass.getInstance()))

        httpClientBuilder.interceptors().add(Interceptor { chain ->
            val original = chain.request()
            // Customize the request

            val request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Version", BuildConfig.VERSION_CODE.toString())
                    //.header("Authorization", "JWT "+PreferenceHelper.getInstance().authToken!!)
                    .method(original.method, original.body)
            chain.proceed(request.build())
        }
        )
//        httpClientBuilder.addInterceptor(loggingInterceptor())
        return httpClientBuilder.build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.apply {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    companion object {
        private const val HTTP_READ_TIMEOUT = 20000
        private const val HTTP_WRITE_TIMEOUT = 20000
        private const val HTTP_CONNECT_TIMEOUT = 60000

        private var INSTANCE: WebAPISeviceFactory? = null

        fun newInstance(): WebAPISeviceFactory {
            if (INSTANCE == null) {
                INSTANCE = WebAPISeviceFactory()
            }
            return INSTANCE as WebAPISeviceFactory
        }
    }

}



