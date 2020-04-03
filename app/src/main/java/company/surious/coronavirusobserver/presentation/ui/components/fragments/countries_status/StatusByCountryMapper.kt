package company.surious.coronavirusobserver.presentation.ui.components.fragments.countries_status

import company.surious.coronavirusobserver.domain.entities.PatientState
import company.surious.coronavirusobserver.domain.entities.StatusEntity

object StatusByCountryMapper {

    fun map(status: StatusEntity, patientState: PatientState): List<StatusByCountryModel> =
        status.countryStatus.map { countryStatus ->
            val count = when (patientState) {
                PatientState.INFECTED -> countryStatus.infected
                PatientState.DEAD -> countryStatus.dead
                PatientState.RECOVERED -> countryStatus.recovered
            }
            StatusByCountryModel(
                count,
                countryStatus.countryName
            )
        }.filter { it.count != 0 }.sortedByDescending {
            it.count
        }.sortedWith(StatusByCountryModelComparator)
}