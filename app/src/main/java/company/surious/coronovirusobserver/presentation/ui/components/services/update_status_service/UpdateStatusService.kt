package company.surious.coronovirusobserver.presentation.ui.components.services.update_status_service

import android.content.Intent
import android.os.IBinder
import company.surious.coronovirusobserver.domain.use_case.GetStatusUseCase
import dagger.android.DaggerService
import javax.inject.Inject

class UpdateStatusService : DaggerService() {

    @Inject
    lateinit var getStatusUseCase: GetStatusUseCase

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
