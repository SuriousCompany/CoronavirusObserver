package company.surious.coronovirusobserver.data.di

import company.surious.coronovirusobserver.data.network.CoronovirusApi
import company.surious.coronovirusobserver.data.network.RetrofitClient
import company.surious.coronovirusobserver.data.repositories.StatusRepositoryImpl
import company.surious.coronovirusobserver.domain.repositories.StatusRepository
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