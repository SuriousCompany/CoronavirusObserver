package company.surious.coronavirusobserver.presentation.ui.components.fragments.news

import company.surious.coronavirusobserver.presentation.di.ViewModelModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [ViewModelModule::class])
interface NewsComponent : AndroidInjector<NewsFragment> {
    @Subcomponent.Factory
    interface Builder : AndroidInjector.Factory<NewsFragment>
}