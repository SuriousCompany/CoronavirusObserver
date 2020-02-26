package company.surious.coronovirusobserver.presentation.ui.components.fragments.countries_status

import company.surious.coronovirusobserver.presentation.di.ViewModelModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [ViewModelModule::class])
interface StatusByPatientsStateComponent : AndroidInjector<StatusByPatientsStateFragment> {
    @Subcomponent.Factory
    interface Builder : AndroidInjector.Factory<StatusByPatientsStateFragment>
}
