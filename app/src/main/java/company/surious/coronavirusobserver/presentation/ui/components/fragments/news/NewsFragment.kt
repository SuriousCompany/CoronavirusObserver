package company.surious.coronavirusobserver.presentation.ui.components.fragments.news


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import company.surious.coronavirusobserver.R
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment() {
    private val newsUrl = "https://www.nytimes.com/2020/02/13/world/asia/china-coronavirus.html"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNewsWebView()
    }

    override fun onStart() {
        super.onStart()
        newsWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                newsSwipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onStop() {
        super.onStop()
        newsWebView.webViewClient = null
    }

    private fun initNewsWebView() {
        with(newsWebView) {
            newsSwipeRefreshLayout.isRefreshing = true
            loadUrl(newsUrl)
            newsSwipeRefreshLayout.setOnRefreshListener {
                newsSwipeRefreshLayout.isRefreshing = true
                reload()
            }
        }
    }
}
