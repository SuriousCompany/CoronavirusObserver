package company.surious.coronavirusobserver.presentation.ui.components.views.network_state_snackbar

import android.view.View
import androidx.databinding.Observable
import com.google.android.material.snackbar.Snackbar
import company.surious.coronavirusobserver.R
import java.lang.ref.WeakReference

class NetworkStateSnackbarDelegate(private val networkStateViewModel: NetworkStateViewModel) {
    private var viewReference: WeakReference<View>? = null
    private var snackbar: Snackbar? = null

    private val callback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            updateSnackbar(networkStateViewModel.observableNetworkState.get())
        }
    }

    private fun updateSnackbar(hasConnection: Boolean) {
        if (hasConnection) {
            hideSnackbar()
        } else {
            showSnackbar()
        }
    }

    fun attach(targetView: View) {
        viewReference?.clear()
        viewReference = WeakReference(targetView)
        networkStateViewModel.init()
        networkStateViewModel.observableNetworkState.addOnPropertyChangedCallback(callback)
    }

    fun detach() {
        networkStateViewModel.observableNetworkState.removeOnPropertyChangedCallback(callback)
        viewReference?.clear()
        viewReference = null
    }

    private fun showSnackbar() {
        viewReference?.get()?.let { view ->
            if (snackbar != null) {
                with(snackbar!!) {
                    if (!isShown) {
                        show()
                    }
                }
            } else {
                snackbar =
                    Snackbar.make(view, R.string.no_network_connection, Snackbar.LENGTH_INDEFINITE)
                snackbar!!.show()
            }
        }
    }

    private fun hideSnackbar() {
        snackbar?.dismiss()
    }

}