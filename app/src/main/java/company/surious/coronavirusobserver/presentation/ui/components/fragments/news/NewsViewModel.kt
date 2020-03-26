package company.surious.coronavirusobserver.presentation.ui.components.fragments.news

import androidx.lifecycle.ViewModel
import com.crashlytics.android.Crashlytics
import company.surious.coronavirusobserver.domain.use_case.GetNewsLinkUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val getNewsLinkUseCase: GetNewsLinkUseCase) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val newsState = NewsState()

    fun updateUrl() {
        compositeDisposable.add(
            getNewsLinkUseCase.execute(null)
                .subscribe(newsState.newsUrl::set, Crashlytics::logException)
        )
    }
}