package company.surious.coronavirusobserver.presentation.ui.components.fragments.countries_status


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.Observable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import company.surious.coronavirusobserver.R
import company.surious.coronavirusobserver.domain.entities.PatientState
import company.surious.coronavirusobserver.presentation.ui.base.ViewModelFactory
import company.surious.coronavirusobserver.presentation.ui.components.fragments.status.StatusState
import company.surious.coronavirusobserver.presentation.ui.components.fragments.status.StatusViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_status_by_patients_state.*
import javax.inject.Inject

class StatusByPatientsStateFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var statusViewModel: StatusViewModel
    private lateinit var statusCallback: Observable.OnPropertyChangedCallback
    private lateinit var adapter: StatusByPatientsStatePageAdapter
    private lateinit var statusState: StatusState
    private var currentPatientState = PatientState.INFECTED

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_status_by_patients_state, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusViewModel = ViewModelProvider(this, viewModelFactory)[StatusViewModel::class.java]
        statusState = statusViewModel.statusState
        adapter = StatusByPatientsStatePageAdapter(
            childFragmentManager,
            arrayOf(
                getString(R.string.confirmed),
                getString(R.string.dead),
                getString(R.string.recovered)
            )
        )
        setupPager()
    }

    fun showState(patientState: PatientState) {
        currentPatientState = patientState
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            updateState()
        }
    }

    override fun onStart() {
        super.onStart()
        statusCallback = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                adapter.update(statusState.statusEntity.get()!!)
            }
        }
        statusState.statusEntity.addOnPropertyChangedCallback(statusCallback)
        updateState()
    }

    override fun onResume() {
        super.onResume()
        if (statusState.statusEntity.get() == null) {
            statusViewModel.updateStatus()
        }
    }

    private fun updateState() {
        if (adapter.states[statusViewPager.currentItem] != currentPatientState) {
            statusViewPager.currentItem = adapter.states.indexOf(currentPatientState)
        }
    }

    override fun onStop() {
        super.onStop()
        statusState.statusEntity.removeOnPropertyChangedCallback(statusCallback)
    }

    private fun setupPager() {
        statusViewPager.adapter = adapter
        statusTabLayout.setupWithViewPager(statusViewPager)
        statusViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                updateTabColors(position)
            }
        })
    }

    private fun updateTabColors(selectedPosition: Int) {
        val selectedColor =
            when (selectedPosition) {
                1 -> R.color.colorRed
                2 -> R.color.colorGreen
                else -> R.color.colorWhite
            }
        statusTabLayout.setTabTextColors(
            getColor(R.color.colorWhite),
            getColor(selectedColor)
        )
        statusTabLayout.setSelectedTabIndicatorColor(getColor(selectedColor))
    }

    private fun getColor(color: Int) = ContextCompat.getColor(requireContext(), color)
}
