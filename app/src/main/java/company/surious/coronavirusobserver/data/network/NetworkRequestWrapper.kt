package company.surious.coronavirusobserver.data.network

import company.surious.coronavirusobserver.device.network.ConnectionStateProvider
import io.reactivex.Single

class NetworkRequestWrapper(private val connectionStateProvider: ConnectionStateProvider) {

    fun <T> wrap(request: Single<T>): Single<T> =
        if (connectionStateProvider.hasInternetConnection()) {
            request
        } else {
            wrapWithConnectionStateObserver(request)
        }

    private fun <T> wrapWithConnectionStateObserver(request: Single<T>): Single<T> =
        connectionStateProvider.observeInternetConnection()
            .filter { it }
            .firstOrError()
            .flatMap { request }
}