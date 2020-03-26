package company.surious.coronavirusobserver.presentation.di

import androidx.lifecycle.ViewModel
import company.surious.coronavirusobserver.presentation.ui.components.fragments.news.NewsViewModel
import company.surious.coronavirusobserver.presentation.ui.components.fragments.status.StatusViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(StatusViewModel::class)
    internal abstract fun bindStatusViewModel(viewModel: StatusViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    internal abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel

}