package company.surious.coronavirusobserver.data.network

import company.surious.coronavirusobserver.data.repositories.news.CnnApi
import company.surious.coronavirusobserver.data.repositories.news.CnnRetrofitClient
import company.surious.coronavirusobserver.data.repositories.news.NewsRepositoryImpl
import company.surious.coronavirusobserver.data.repositories.status.ArcgisRetrofitClient
import company.surious.coronavirusobserver.data.repositories.status.CoronovirusApi
import company.surious.coronavirusobserver.data.repositories.status.StatusRepositoryImpl
import company.surious.coronavirusobserver.data.resources.ResourcesModule
import company.surious.coronavirusobserver.device.network.ConnectionStateProvider
import company.surious.coronavirusobserver.domain.repositories.NewsRepository
import company.surious.coronavirusobserver.domain.repositories.StatusRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
object RepositoriesModule {
    private const val ARCGIS_RETROFIT = "arcgisRetrofit"
    private const val CNN_RETROFIT = "cnnRetrofit"

    @Provides
    @Singleton
    @JvmStatic
    @Named(ARCGIS_RETROFIT)
    fun provideArcgisRetrofit() = ArcgisRetrofitClient().getRetrofit()

    @Provides
    @Singleton
    @JvmStatic
    @Named(CNN_RETROFIT)
    fun provideCnnRetrofit() = CnnRetrofitClient().getRetrofit()

    @Provides
    @Singleton
    @JvmStatic
    fun provideCoronovirusApi(@Named(ARCGIS_RETROFIT) retrofit: Retrofit): CoronovirusApi =
        retrofit.create(CoronovirusApi::class.java)

    @Provides
    @Singleton
    @JvmStatic
    fun provideCnnApi(@Named(CNN_RETROFIT) retrofit: Retrofit): CnnApi =
        retrofit.create(CnnApi::class.java)

    @Provides
    @Singleton
    @JvmStatic
    fun provideNetworkRequestWrapper(provider: ConnectionStateProvider): NetworkRequestWrapper =
        NetworkRequestWrapper(provider)

    @Provides
    @Singleton
    @JvmStatic
    fun provideNewsRepository(
        api: CnnApi,
        @Named(ResourcesModule.DEFAULT_NEWS_URL) defaultNewsLink: String
    ): NewsRepository = NewsRepositoryImpl(api, defaultNewsLink)

    @Provides
    @Singleton
    @JvmStatic
    fun provideReportsRepository(
        api: CoronovirusApi,
        networkRequestWrapper: NetworkRequestWrapper
    ): StatusRepository = StatusRepositoryImpl(api, networkRequestWrapper)
}