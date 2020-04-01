package company.surious.coronavirusobserver.presentation.ui.components.views.network_state_snackbar

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import company.surious.coronavirusobserver.device.network.ConnectionStateProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NetworkStateViewModel @Inject constructor(private val networkStateProvider: ConnectionStateProvider) :
    ViewModel() {
    private val subscriptions = CompositeDisposable()

    val observableNetworkState = ObservableBoolean(true)

    fun init() {
        subscriptions.add(
            networkStateProvider.observeInternetConnection().subscribe(observableNetworkState::set)
        )
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}