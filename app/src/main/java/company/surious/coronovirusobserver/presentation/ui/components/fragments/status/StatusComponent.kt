package company.surious.coronovirusobserver.presentation.ui.components.fragments.status

import company.surious.coronovirusobserver.presentation.di.ViewModelModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [ViewModelModule::class])
interface StatusComponent : AndroidInjector<StatusFragment> {
    @Subcomponent.Factory
    interface Builder : AndroidInjector.Factory<StatusFragment>
}