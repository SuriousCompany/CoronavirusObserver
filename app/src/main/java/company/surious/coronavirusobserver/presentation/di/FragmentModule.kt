package company.surious.coronavirusobserver.presentation.di

import company.surious.coronavirusobserver.presentation.ui.components.fragments.countries_status.StatusByPatientsStateComponent
import company.surious.coronavirusobserver.presentation.ui.components.fragments.countries_status.StatusByPatientsStateFragment
import company.surious.coronavirusobserver.presentation.ui.components.fragments.news.NewsComponent
import company.surious.coronavirusobserver.presentation.ui.components.fragments.news.NewsFragment
import company.surious.coronavirusobserver.presentation.ui.components.fragments.status.StatusComponent
import company.surious.coronavirusobserver.presentation.ui.components.fragments.status.StatusFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
    subcomponents = [StatusComponent::class, StatusByPatientsStateComponent::class,
        NewsComponent::class]
)
interface FragmentModule {

    @Binds
    @IntoMap
    @ClassKey(StatusFragment::class)
    fun bindStatusFragment(builder: StatusComponent.Builder): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(StatusByPatientsStateFragment::class)
    fun bindCountriesStatusFragment(builder: StatusByPatientsStateComponent.Builder): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(NewsFragment::class)
    fun bindNewsFragment(builder: NewsComponent.Builder): AndroidInjector.Factory<*>
}