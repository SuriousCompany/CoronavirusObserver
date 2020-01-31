package company.surious.coronovirusobserver.presentation.di

import androidx.lifecycle.ViewModel
import company.surious.coronovirusobserver.presentation.ui.components.fragments.status.StatusViewModel
import company.surious.mediator_presentation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(StatusViewModel::class)
    internal abstract fun bindStatusViewModel(viewModel: StatusViewModel): ViewModel

}