package company.surious.coronavirusobserver.presentation.ui.binding

import android.webkit.WebView
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.databinding.ObservableField

@BindingAdapter("loadUrl")
fun bindLoadUrlAdapter(webView: WebView, observableUrlField: ObservableField<String>) {
    observableUrlField.addOnPropertyChangedCallback(
        object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val url = observableUrlField.get()
                if (url != null) {
                    webView.loadUrl(url)
                }
            }
        })
}