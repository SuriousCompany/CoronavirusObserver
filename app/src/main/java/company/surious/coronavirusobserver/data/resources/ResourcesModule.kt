package company.surious.coronavirusobserver.data.resources

import android.app.Application
import company.surious.coronavirusobserver.R
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object ResourcesModule {
    const val DEFAULT_NEWS_URL = "defaultNewsUrl"

    @Provides
    @Singleton
    @JvmStatic
    @Named(DEFAULT_NEWS_URL)
    fun provideNewsUrl(app: Application): String = app.getString(R.string.default_news_url)

}