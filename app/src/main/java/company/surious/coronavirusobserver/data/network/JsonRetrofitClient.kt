package company.surious.coronavirusobserver.data.network

import company.surious.coronavirusobserver.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class JsonRetrofitClient {

    protected abstract val baseUrl: String

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(createLoggingInterceptor())
        }
        return builder.build()
    }

    private fun createLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}