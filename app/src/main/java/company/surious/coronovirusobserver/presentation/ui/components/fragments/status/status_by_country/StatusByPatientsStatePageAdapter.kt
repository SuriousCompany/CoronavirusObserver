package company.surious.coronovirusobserver.presentation.ui.components.fragments.status.status_by_country

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import company.surious.coronovirusobserver.domain.entities.PatientState
import company.surious.coronovirusobserver.domain.entities.StatusEntity

class StatusByPatientsStatePageAdapter(
    fragmentManager: FragmentManager,
    private val statusEntity: StatusEntity,
    private val tabNames: Array<String>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val states = arrayOf(PatientState.INFECTED, PatientState.DEAD, PatientState.RECOVERED)
    private val data = ArrayList<CountriesStatusFragment>()

    init {
        states.forEach {
            data.add(generateFragment(it))
        }
    }

    private fun generateFragment(patientState: PatientState) =
        CountriesStatusFragment.newInstance(statusEntity, patientState)

    override fun getItem(position: Int): Fragment = data[position]

    override fun getCount(): Int = states.size

    override fun getPageTitle(position: Int): CharSequence? = tabNames[position]

}