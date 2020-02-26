package company.surious.coronovirusobserver.presentation.ui.components.activities.main

import company.surious.coronovirusobserver.domain.entities.PatientState
import company.surious.coronovirusobserver.domain.entities.StatusEntity

interface NavigationProvider {
    fun showCountriesFragment()
    fun showCountriesFragment(statusEntity: StatusEntity, patientState: PatientState)
}