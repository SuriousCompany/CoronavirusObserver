package company.surious.coronavirusobserver.presentation.ui.components.fragments.news


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import company.surious.coronavirusobserver.R
import company.surious.coronavirusobserver.databinding.FragmentNewsBinding
import company.surious.coronavirusobserver.presentation.ui.base.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject


class NewsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsState: NewsState

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNewsWebView()
        binding.lifecycleOwner = this
        newsViewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        newsState = newsViewModel.newsState
        binding.newsState = newsState
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

    override fun onResume() {
        super.onResume()
        if (newsState.newsUrl.get() == null) {
            newsViewModel.updateUrl()
        }
    }

    override fun onStop() {
        super.onStop()
        newsWebView.webViewClient = null
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initNewsWebView() {
        with(newsWebView) {
            newsSwipeRefreshLayout.isRefreshing = true
            newsSwipeRefreshLayout.setOnRefreshListener {
                newsSwipeRefreshLayout.isRefreshing = true
                reload()
            }
            settings.javaScriptEnabled = true
        }
    }
}
