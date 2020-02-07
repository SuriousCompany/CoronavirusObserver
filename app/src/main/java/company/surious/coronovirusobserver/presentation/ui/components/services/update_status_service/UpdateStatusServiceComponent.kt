package company.surious.coronovirusobserver.presentation.ui.components.services.update_status_service

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface UpdateStatusServiceComponent : AndroidInjector<UpdateStatusService> {
    @Subcomponent.Factory
    interface Builder : AndroidInjector.Factory<UpdateStatusService>
}