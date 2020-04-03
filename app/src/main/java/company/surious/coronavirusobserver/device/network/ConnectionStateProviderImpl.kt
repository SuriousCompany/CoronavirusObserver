package company.surious.coronavirusobserver.device.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class ConnectionStateProviderImpl(private val connectivityManager: ConnectivityManager) :
    ConnectionStateProvider {

    private val networkStatePublishSubject = BehaviorSubject.create<Boolean>()

    init {
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
        registerNetworkCallback(networkRequest)
    }

    private fun registerNetworkCallback(networkRequest: NetworkRequest) {
        connectivityManager.registerNetworkCallback(networkRequest,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network?) {
                    super.onAvailable(network)
                    networkStatePublishSubject.onNext(hasInternetConnection())
                }

                override fun onLost(network: Network?) {
                    super.onLost(network)
                    networkStatePublishSubject.onNext(hasInternetConnection())
                }
            })
        networkStatePublishSubject.onNext(hasInternetConnection())
    }

    override fun hasInternetConnection(): Boolean {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo?.isConnected ?: false
    }


    override fun observeInternetConnection(): Observable<Boolean> = networkStatePublishSubject
}