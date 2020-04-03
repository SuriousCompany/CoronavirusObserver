package company.surious.coronavirusobserver.presentation.ui.components.activities.main

import company.surious.coronavirusobserver.presentation.di.ViewModelModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [ViewModelModule::class])
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    interface Builder : AndroidInjector.Factory<MainActivity>
}