package company.surious.coronovirusobserver.presentation.ui.components.fragments.status

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import company.surious.coronovirusobserver.domain.entities.StatusEntity

data class StatusState(
    var statusEntity: ObservableField<StatusEntity> = ObservableField(StatusEntity()),
    val infected: ObservableInt = ObservableInt(),
    var dead: ObservableInt = ObservableInt(),
    var recovered: ObservableInt = ObservableInt(),
    var isLoading: ObservableBoolean = ObservableBoolean(true)
)