package company.surious.coronovirusobserver.presentation.di

import company.surious.coronovirusobserver.presentation.ui.components.fragments.status.StatusComponent
import company.surious.coronovirusobserver.presentation.ui.components.fragments.status.StatusFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [StatusComponent::class])
interface FragmentModule {

    @Binds
    @IntoMap
    @ClassKey(StatusFragment::class)
    fun bindStatusFragment(builder: StatusComponent.Builder): AndroidInjector.Factory<*>
}