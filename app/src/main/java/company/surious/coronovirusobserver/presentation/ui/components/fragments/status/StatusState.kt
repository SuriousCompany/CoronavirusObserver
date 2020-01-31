package company.surious.coronovirusobserver.presentation.ui.components.fragments.status

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt

data class StatusState(
    val infected: ObservableInt = ObservableInt(),
    var dead: ObservableInt = ObservableInt(),
    var recovered: ObservableInt = ObservableInt(),
    var isLoading: ObservableBoolean = ObservableBoolean(true)
)