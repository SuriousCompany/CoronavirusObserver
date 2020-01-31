package company.surious.coronovirusobserver.data.network

import company.surious.coronovirusobserver.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(createQueryInterceptor())
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(createLoggingInterceptor())
        }
        return builder.build()
    }

    private fun createQueryInterceptor(): Interceptor = Interceptor {
        val original = it.request()
        val originalUrl = original.url()
        val modifiedUrl = originalUrl.newBuilder()
            .addQueryParameter("key", BuildConfig.GOOGLE_API_KEY)
            .build()
        val modifiedRequest = original.newBuilder().url(modifiedUrl).build()
        it.proceed(modifiedRequest)
    }

    private fun createLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


}