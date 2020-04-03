package company.surious.coronavirusobserver.presentation.ui.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("onRefresh")
fun bindRefreshListener(refreshLayout: SwipeRefreshLayout, method: () -> Unit) {
    refreshLayout.setOnRefreshListener {
        method.invoke()
    }
}

@BindingAdapter("refreshing")
fun bindRefreshListener(refreshLayout: SwipeRefreshLayout, refreshing: Boolean) {
    refreshLayout.isRefreshing = refreshing
}