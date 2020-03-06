package company.surious.coronavirusobserver.presentation.di

import company.surious.coronavirusobserver.presentation.ui.components.fragments.countries_status.StatusByPatientsStateComponent
import company.surious.coronavirusobserver.presentation.ui.components.fragments.countries_status.StatusByPatientsStateFragment
import company.surious.coronavirusobserver.presentation.ui.components.fragments.status.StatusComponent
import company.surious.coronavirusobserver.presentation.ui.components.fragments.status.StatusFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [StatusComponent::class, StatusByPatientsStateComponent::class])
interface FragmentModule {

    @Binds
    @IntoMap
    @ClassKey(StatusFragment::class)
    fun bindStatusFragment(builder: StatusComponent.Builder): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(StatusByPatientsStateFragment::class)
    fun bindCountriesStatusFragment(builder: StatusByPatientsStateComponent.Builder): AndroidInjector.Factory<*>
}