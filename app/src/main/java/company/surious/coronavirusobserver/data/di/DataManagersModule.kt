package company.surious.coronavirusobserver.data.di

import company.surious.coronavirusobserver.data.network.CoronovirusApi
import company.surious.coronavirusobserver.data.network.RetrofitClient
import company.surious.coronavirusobserver.data.repositories.StatusRepositoryImpl
import company.surious.coronavirusobserver.domain.repositories.StatusRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object DataManagersModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit() = RetrofitClient().getRetrofit()

    @Provides
    @Singleton
    @JvmStatic
    fun provideCoronovirusApi(retrofit: Retrofit): CoronovirusApi =
        retrofit.create(CoronovirusApi::class.java)

    @Provides
    @Singleton
    @JvmStatic
    fun provideReportsRepository(api: CoronovirusApi): StatusRepository = StatusRepositoryImpl(api)
}