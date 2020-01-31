package company.surious.coronovirusobserver.presentation.ui.components.fragments.status

import company.surious.coronovirusobserver.domain.entities.PatientState
import company.surious.coronovirusobserver.domain.entities.StatusEntity

object StatusByCountryMapper {

    fun map(status: StatusEntity, patientState: PatientState): List<StatusByCountryModel> =
        status.countryStatus.values.map { countryStatus ->
            val count = when (patientState) {
                PatientState.INFECTED -> countryStatus.infected
                PatientState.DEAD -> countryStatus.dead
                PatientState.RECOVERED -> countryStatus.recovered
            }
            StatusByCountryModel(count, countryStatus.countryName)
        }.filter { it.count != 0 }.sortedByDescending { it.count }
}