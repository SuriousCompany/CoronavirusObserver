package company.surious.coronavirusobserver.presentation.ui.components.activities.main

import company.surious.coronavirusobserver.domain.entities.PatientState

interface NavigationProvider {
    fun showCountriesFragment(patientState: PatientState)
}