package company.surious.coronovirusobserver.presentation.ui.components.fragments.countries_status

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import company.surious.coronovirusobserver.domain.entities.PatientState
import company.surious.coronovirusobserver.domain.entities.StatusEntity

class StatusByPatientsStatePageAdapter(
    fragmentManager: FragmentManager,
    private val tabNames: Array<String>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val states = arrayOf(PatientState.INFECTED, PatientState.DEAD, PatientState.RECOVERED)
    private val data = ArrayList<CountriesStatusFragment>()
    private var dataCount = 0

    fun update(statusEntity: StatusEntity) {
        states.forEach {
            data.add(generateFragment(statusEntity, it))
        }
        dataCount = states.size
        notifyDataSetChanged()
    }

    private fun generateFragment(statusEntity: StatusEntity, patientState: PatientState) =
        CountriesStatusFragment.newInstance(statusEntity, patientState)

    override fun getItem(position: Int): Fragment = data[position]

    override fun getCount(): Int = dataCount

    override fun getPageTitle(position: Int): CharSequence? = tabNames[position]

}