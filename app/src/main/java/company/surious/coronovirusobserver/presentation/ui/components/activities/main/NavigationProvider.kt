package company.surious.coronovirusobserver.presentation.ui.components.activities.main

import company.surious.coronovirusobserver.domain.entities.PatientState

interface NavigationProvider {
    fun showCountriesFragment(patientState: PatientState)
}