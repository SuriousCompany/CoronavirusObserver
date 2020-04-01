package company.surious.coronavirusobserver.device.network

import io.reactivex.Observable


interface ConnectionStateProvider {
    fun hasInternetConnection(): Boolean
    fun observeInternetConnection(): Observable<Boolean>
}