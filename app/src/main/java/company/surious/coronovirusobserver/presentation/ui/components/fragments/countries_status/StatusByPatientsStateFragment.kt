package company.surious.coronovirusobserver.presentation.ui.components.fragments.countries_status


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import company.surious.coronovirusobserver.R
import company.surious.coronovirusobserver.presentation.ui.base.ViewModelFactory
import company.surious.coronovirusobserver.presentation.ui.components.fragments.status.StatusViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_status_by_patients_state.*
import javax.inject.Inject

class StatusByPatientsStateFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val args: StatusByPatientsStateFragmentArgs by navArgs()

    private lateinit var statusViewModel: StatusViewModel
    private lateinit var statusCallback: Observable.OnPropertyChangedCallback
    private lateinit var adapter: StatusByPatientsStatePageAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_status_by_patients_state, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusViewModel = ViewModelProvider(this, viewModelFactory)[StatusViewModel::class.java]
        adapter = StatusByPatientsStatePageAdapter(
            childFragmentManager,
            arrayOf(
                getString(R.string.confirmed),
                getString(R.string.dead),
                getString(R.string.recovered)
            )
        )
        setupPager()
        initStatus()
    }

    override fun onStart() {
        super.onStart()
        statusCallback = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                adapter.update(statusViewModel.statusState.statusEntity.get()!!)
            }
        }
        statusViewModel.statusState.statusEntity.addOnPropertyChangedCallback(statusCallback)
    }

    override fun onStop() {
        super.onStop()
        statusViewModel.statusState.statusEntity.removeOnPropertyChangedCallback(statusCallback)
    }

    private fun initStatus() {
        args.statusEntity?.let { adapter.update(it) } ?: run {
            statusViewModel.updateStatus()
        }
    }

    private fun setupPager() {
        statusViewPager.adapter = adapter
        val selectedPosition = adapter.states.indexOf(args.patientState)
        statusViewPager.currentItem = selectedPosition
        updateTabColors(selectedPosition)
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
