package company.surious.coronovirusobserver.presentation.di

import company.surious.coronovirusobserver.presentation.ui.components.activities.main.MainActivity
import company.surious.coronovirusobserver.presentation.ui.components.activities.main.MainActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainActivityComponent::class])
interface ActivityModule {
    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    fun bindMainActivity(builder: MainActivityComponent.Builder): AndroidInjector.Factory<*>

}