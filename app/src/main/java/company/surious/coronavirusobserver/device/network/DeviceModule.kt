package company.surious.coronavirusobserver.device.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DeviceModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideConnectivityManager(application: Application) =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    @Singleton
    @JvmStatic
    fun provideConnectionStateProvider(connectivityManager: ConnectivityManager): ConnectionStateProvider =
        ConnectionStateProviderImpl(connectivityManager)
}