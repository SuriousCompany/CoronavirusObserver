package company.surious.coronavirusobserver.domain.use_case

import company.surious.coronavirusobserver.device.network.ConnectionStateProvider
import company.surious.coronavirusobserver.domain.use_case.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class ObserveNetworkStateUseCase @Inject constructor(private val stateProvider: ConnectionStateProvider) :
    ObservableUseCase<Void?, Boolean>() {

    override fun createObservable(params: Void?): Observable<Boolean> =
        stateProvider.observeInternetConnection()
}