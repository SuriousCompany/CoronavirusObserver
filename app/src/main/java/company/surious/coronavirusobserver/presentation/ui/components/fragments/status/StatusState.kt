package company.surious.coronavirusobserver.presentation.ui.components.fragments.status

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import company.surious.coronavirusobserver.domain.entities.StatusEntity

data class StatusState(
    var statusEntity: ObservableField<StatusEntity> = ObservableField(),
    val infected: ObservableField<String> = ObservableField(),
    var dead: ObservableField<String> = ObservableField(),
    var recovered: ObservableField<String> = ObservableField(),
    var isLoading: ObservableBoolean = ObservableBoolean(true)
)