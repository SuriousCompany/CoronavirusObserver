package company.surious.coronovirusobserver.presentation.di

import company.surious.coronovirusobserver.presentation.ui.components.services.update_status_service.UpdateStatusService
import company.surious.coronovirusobserver.presentation.ui.components.services.update_status_service.UpdateStatusServiceComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [UpdateStatusServiceComponent::class])
interface ServiceModule {
    @Binds
    @IntoMap
    @ClassKey(UpdateStatusService::class)
    fun bindUpdateStatusService(builder: UpdateStatusServiceComponent.Builder): AndroidInjector.Factory<*>
}